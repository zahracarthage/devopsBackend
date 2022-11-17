ckage com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.StockRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class StockTest {

    @Mock
    StockRepository stockRepository;

    @InjectMocks
    StockServiceImpl stockService;

    Stock stock1 = new Stock("Charcuterie", 730, 100);

    List<Stock> stockList = new ArrayList<Stock>() {

        {
            add(new Stock("Produits Laitiers", 5200, 1000));
            add(new Stock("Biscuits", 7800, 2500));
            add(new Stock("Electroménagers", 300, 50));

        }};

    @Test
    @Order(0)
    void addStockTest()
    {
        Stock stock = new Stock("Produits Laitiers", 5200, 1000);
        for (Stock s : stockList) {
            stockService.addStock(s);
            verify(stockRepository, times(1)).save(s);
        }
    }

    @Test
    @Order(3)
    void deleteAllStockTest() {
        stockRepository.deleteAll();
        assertEquals(0,stockRepository.findAll().spliterator().estimateSize());
    }

    @Test
    @Order(1)
    void retrieveAllStockTest() {
        when(stockService.retrieveAllStocks()).thenReturn(stockList);
        List<Stock> stockList1 = stockService.retrieveAllStocks();
        Assertions.assertEquals(3, stockList1.size());
    }

    @Test
    @Order(2)
    void deleteStockTest(){
        stock1.setIdStock(2L);
        Mockito.lenient().when(stockRepository.findById(stock1.getIdStock())).thenReturn(Optional.of(stock1));
        stockService.deleteStock(2L);
        verify(stockRepository).deleteById(stock1.getIdStock());
    }





}

