package com.jmcaldera.cleanfootball.competitiondetails;

import com.jmcaldera.cleanfootball.TestUseCaseScheduler;
import com.jmcaldera.cleanfootball.UseCaseHandler;
import com.jmcaldera.cleanfootball.competitiondetails.model.standings.Standing;
import com.jmcaldera.cleanfootball.competitiondetails.model.standings.StandingItem;
import com.jmcaldera.cleanfootball.competitiondetails.model.usecase.GetStandings;
import com.jmcaldera.cleanfootball.data.CompetitionsDataSource;
import com.jmcaldera.cleanfootball.data.CompetitionsRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jmcaldera on 27/05/17.
 */

public class CompetitionDetailsPresenterTest {

    private static Standing STANDING;

    private final int COMPETITION_ID = 436;

    @Mock
    private CompetitionsRepository mCompetitionsRepository;

    @Mock
    private CompetitionDetailsContract.View mCompetitionsDetailsView;

    @Captor
    private ArgumentCaptor<CompetitionsDataSource.LoadStandingsCallback> mLoadStandingsCallbackCaptor;

    private CompetitionDetailsPresenter mCompDetailsPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Espera que la vista este activa
        when(mCompetitionsDetailsView.isActive()).thenReturn(true);

        // Inicializa con datos falsos
        // Crear dos equipos
        List<StandingItem> teams = new ArrayList<>();
        teams.add(new StandingItem("Real Madrid", 1, 93, 29, 3, 6));
        teams.add(new StandingItem("FC Barcelona", 2, 90, 28, 4, 6));

        STANDING = new Standing("Primera Division 2016/17", 38, teams);
    }

    @Test
    public void loadStandingFromRemoteRepositoryAndShowInTextView() {
        // Instancia el presenter
        mCompDetailsPresenter = givenCompetitionDetailsPresenter();
        mCompDetailsPresenter.start();

        // Carga el standing del modelo y se captura el callback
        verify(mCompetitionsRepository).getStandings(eq(COMPETITION_ID), mLoadStandingsCallbackCaptor.capture());
        mLoadStandingsCallbackCaptor.getValue().onStandingsLoaded(STANDING);

        // Se muestra el loadingIndicator
        InOrder inOrder = inOrder(mCompetitionsDetailsView);
        inOrder.verify(mCompetitionsDetailsView).setLoadingIndicator(true);

        // Se oculta
        inOrder.verify(mCompetitionsDetailsView).setLoadingIndicator(false);

        ArgumentCaptor<List> showStandingsArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mCompetitionsDetailsView).showStandings(showStandingsArgumentCaptor.capture());
        assertTrue(showStandingsArgumentCaptor.getValue().size() == 2);

    }

    private CompetitionDetailsPresenter givenCompetitionDetailsPresenter() {
        UseCaseHandler useCaseHandler = new UseCaseHandler(new TestUseCaseScheduler());
        GetStandings getStandings = new GetStandings(mCompetitionsRepository);

        return new CompetitionDetailsPresenter(COMPETITION_ID, mCompetitionsDetailsView,
                getStandings, useCaseHandler);
    }

}
