package com.jmcaldera.cleanfootball.competitions;

import com.google.common.collect.Lists;
import com.jmcaldera.cleanfootball.TestUseCaseScheduler;
import com.jmcaldera.cleanfootball.UseCaseHandler;
import com.jmcaldera.cleanfootball.competitions.domain.model.Competition;
import com.jmcaldera.cleanfootball.competitions.domain.usecase.GetCompetitions;
import com.jmcaldera.cleanfootball.data.CompetitionsDataSource;
import com.jmcaldera.cleanfootball.data.CompetitionsRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jmcaldera on 04/02/17.
 */
public class CompetitionsPresenterTest {

    private static List<Competition> COMPETITIONS;

    @Mock
    private CompetitionsRepository mCompetitionsRepository;

    @Mock
    private CompetitionsContract.View mCompetitionsView;

    @Captor
    private ArgumentCaptor<CompetitionsDataSource.LoadCompetitionsCallback> mLoadCompsCallbackCaptor;

    private CompetitionsPresenter mCompetitionsPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Espera que la vista este activa
        when(mCompetitionsView.isActive()).thenReturn(true);

        // Inicializar las competitions
        COMPETITIONS = Lists.newArrayList(new Competition(426, "Premier League 2016/17", "PL",
                "2016", 24, "2017-02-04T19:50:14Z"), new Competition(436,
                "Primera Division 2016/17", "PD", "2016", 21, "2017-02-04T21:40:20Z"));
    }

    @Test
    public void loadAllCompetitionsFromRepositoryAndLoadIntoView() {
        // Instancia el presenter
        mCompetitionsPresenter = givenCompetitionsPresenter();
        mCompetitionsPresenter.start();

        // Carga las competitions del modelo, se captura el callback
        verify(mCompetitionsRepository).getCompetitions(mLoadCompsCallbackCaptor.capture());
        mLoadCompsCallbackCaptor.getValue().onCompetitionsLoaded(COMPETITIONS);

        // se muestra el progress indicator
        InOrder inOrder = inOrder(mCompetitionsView);
        inOrder.verify(mCompetitionsView).setLoadingIndicator(true);

        // Luego se oculta el progress indicator y se muestran las competitions
        inOrder.verify(mCompetitionsView).setLoadingIndicator(false);

        ArgumentCaptor<List> showCompetitionsArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mCompetitionsView).showCompetitions(showCompetitionsArgumentCaptor.capture());
        assertTrue(showCompetitionsArgumentCaptor.getValue().size() == 2);

    }

    @Test
    public void clickOnCompetitionShowsCompetitionDetails() {
        // Se crea un stub de competition
        Competition competition = new Competition(426, "Premier League 2016/17", "PL",
                "2016", 24, "2017-02-04T19:50:14Z");

        // Instancia presenter y ejecuta click en competition
        mCompetitionsPresenter = givenCompetitionsPresenter();
        mCompetitionsPresenter.openCompetition(competition);

        // Verifica que se abra la actividad de la competition dada
        verify(mCompetitionsView).showCompetitionsDetails(anyInt());

    }

    private CompetitionsPresenter givenCompetitionsPresenter() {
        UseCaseHandler useCaseHandler = new UseCaseHandler(new TestUseCaseScheduler());
        GetCompetitions getCompetitions = new GetCompetitions(mCompetitionsRepository);

        return new CompetitionsPresenter(mCompetitionsView, getCompetitions, useCaseHandler);
    }
}