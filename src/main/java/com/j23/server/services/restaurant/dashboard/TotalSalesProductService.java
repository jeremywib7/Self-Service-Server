package com.j23.server.services.restaurant.dashboard;

import com.j23.server.models.dashboard.TotalSalesProduct;
import com.j23.server.models.product.Product;
import com.j23.server.repos.dashboard.TotalSalesProductRepository;
import com.j23.server.services.restaurant.time.TimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TotalSalesProductService {

  @Autowired
  private TotalSalesProductRepository totalSalesProductRepository;

  @Autowired
  private TimeService timeService;

  public TotalSalesProduct sumProductProfit(Product product, BigDecimal currentProfit) {
    // check if there are available previous profit with current product in the same month
    Optional<TotalSalesProduct> totalSalesProduct = totalSalesProductRepository.findByProductDetailAndCreatedOnBetween(
      product, timeService.getStartDateTimeOfCurrentMonth(), timeService.getEndDateTimeOfCurrentMonth());
    log.info("total sales : " + totalSalesProduct);

    if (!totalSalesProduct.isPresent()) { // no previous order available
      log.info("creating new record");
      return createProductRecord(product, currentProfit); // return this
    }

    log.info("updating previous record");
    // sum previous total profit with current profit
    BigDecimal previousSales = totalSalesProduct.get().getTotalProfit();
    totalSalesProduct.get().setTotalProfit(new BigDecimal(String.valueOf(previousSales)).add(currentProfit));

    return totalSalesProductRepository.save(totalSalesProduct.get());
  }

  public TotalSalesProduct createProductRecord(Product product, BigDecimal profit) {

    TotalSalesProduct totalSalesProduct = new TotalSalesProduct();
    totalSalesProduct.setProductDetail(product);
    totalSalesProduct.setTotalProfit(profit);
    return totalSalesProductRepository.save(totalSalesProduct);
  }

  public List<TotalSalesProduct> viewTop5Sales() {

    // view top 5 sales in current month
    List<TotalSalesProduct> top5Sales = totalSalesProductRepository.findTop5ByTotalProfitIsAfterAndCreatedOnBetweenOrderByTotalProfitDesc(
      new BigDecimal(0), timeService.getStartDateTimeOfCurrentMonth(), timeService.getEndDateTimeOfCurrentMonth());

    BigDecimal totalProfit = totalSalesProductRepository.sumTotalProfitForCurrentMonth(timeService.getStartDateTimeOfCurrentMonth(),
      timeService.getEndDateTimeOfCurrentMonth());

    if (totalProfit != null) {
      top5Sales.forEach(totalSalesProduct -> {
        totalSalesProduct.setPercentageProfit(getPercentage(totalProfit, totalSalesProduct.getTotalProfit()));
      });
    }

    return top5Sales;
  }

  public List<TotalSalesProduct> viewTop5SalesOnly() {
    // view top 5 sales in current month
    return totalSalesProductRepository.findTop5ByTotalProfitIsAfterOrderByTotalProfitDesc(
            new BigDecimal(0));
  }

  public BigDecimal getPercentage(BigDecimal totalValue, BigDecimal partValue) {
    BigDecimal checkNullTotalValue = totalValue != null ? totalValue : new BigDecimal(0);
    BigDecimal checkNullPartValue = partValue != null ? partValue : new BigDecimal(0);

    assert partValue != null;
    return partValue.divide(totalValue, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
  }

}
