package com.pixelart.week6weekendwalmartchallenge;

import com.pixelart.week6weekendwalmartchallenge.model.ApiResponse;
import com.pixelart.week6weekendwalmartchallenge.model.Item;
import com.pixelart.week6weekendwalmartchallenge.remote.NetworkService;
import com.pixelart.week6weekendwalmartchallenge.ui.mainscreen.MainContract;
import com.pixelart.week6weekendwalmartchallenge.ui.mainscreen.MainPresenter;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    private MainPresenter presenter;

    private ApiResponse apiResponse;

    @Mock
    private MainContract.View view;
    @Mock
    private NetworkService networkService;

    @BeforeClass
    public static void setupSchedulers(){
        final Scheduler scheduler = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(schedulerCallable -> scheduler);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> scheduler);
    }

    @Before
    public void setup(){
        presenter = new MainPresenter(view, networkService);
        apiResponse = new ApiResponse("", "", "", "", Collections.emptyList() );
    }

    @Test
    public void testApiSuccess(){
        when(networkService.getProducts()).thenReturn(Single.just(apiResponse));

       // presenter = new MainPresenter(view, networkService);
        presenter.getProducts();
        verify(view).showProducts(anyList());
    }

    @Test
    public void testApiFail(){
        when(networkService.getProducts()).thenReturn(Single.error(new Throwable()));
       // presenter = new MainPresenter(view, networkService);
        presenter.getProducts();
        verify(view).showError("Failed to fetch data from service");
    }
}
