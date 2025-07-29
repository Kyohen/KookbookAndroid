package kyo.hen.kookbook.features.Creation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable
import kyo.hen.kookbook.features.Detail.TopBarWithAction

@Serializable
object CreationScreenDestination

@Composable
fun CreationScreen(paddingValues: PaddingValues = PaddingValues(), navigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopBarWithAction(title = "Create Recipe") {
                navigateBack()
            }
        },
        modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
    ) { padding ->
        CreationView(modifier = Modifier.padding(padding))
    }
}