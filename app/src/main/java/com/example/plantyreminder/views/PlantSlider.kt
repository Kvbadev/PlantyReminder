import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantyreminder.SampleData
import com.example.plantyreminder.data.models.Plant
import androidx.compose.ui.Alignment.Companion.Center;
import androidx.compose.ui.text.style.TextAlign
import com.example.plantyreminder.views.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun PlantSlider(
    plants: List<Plant>,
    plantsViewModel: HomeViewModel = HomeViewModel()
) {
    var test = plantsViewModel.getPlants();
    Surface(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxSize()
    ) {
        LazyColumn {
            items(plants) {
                Row() {
                    Text(
                        text = it.name, Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.width(20.dp))
                    Text(
                        text = "${it.waterSpan.getEstimatedTimespan()} days",
                        Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PlantSliderPreview() {
    PlantSlider(plants = SampleData.plantsSample)
}
