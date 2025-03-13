import com.esplora.app.wallet.data.remote.FakeEsploraDataSource
import com.esplora.app.wallet.presentation.WalletViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

@OptIn(ExperimentalCoroutinesApi::class)
class WalletViewModelTest {

    private lateinit var fakeEsploraDataSource: FakeEsploraDataSource
    private lateinit var viewModel: WalletViewModel
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        fakeEsploraDataSource = FakeEsploraDataSource()
        // Override Main dispatcher for testing purposes.
        Dispatchers.setMain(testDispatcher)
        viewModel = WalletViewModel(fakeEsploraDataSource)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when fetchAllData is called state is updated with correct balance`() = runTest {
        // Advance time so that the tickerFlow and initial fetch can complete.
        testDispatcher.scheduler.advanceUntilIdle()

        // Collect the latest state.
        val state = viewModel.state.first()

        // Assert that loading has finished and balance has been updated.
        assertFalse(state.isLoading, "State should not be loading")
        // Since FakeEsploraDataSource returns one UTXO with 100_000_000 satoshis,
        // the balance in BTC is 1.0.
        assertEquals(1.0, state.balance, 0.0001)
    }

    @Test
    fun `pullToRefresh triggers data reload and state update`() = runTest {
        // Trigger pull-to-refresh.
        viewModel.pullToRefresh()

        // Advance time for the fetch to finish.
        testDispatcher.scheduler.advanceUntilIdle()

        val refreshedState = viewModel.state.first()

        // The state should now be updated (loading false and balance as expected).
        assertFalse(refreshedState.isLoading, "After pull-to-refresh, state should not be loading")
        assertEquals(1.0, refreshedState.balance, 0.0001)
    }
}