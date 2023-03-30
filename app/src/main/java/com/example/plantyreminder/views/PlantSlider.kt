import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantyreminder.views.HomeViewModel

@Composable
fun PlantSlider(
    plantsViewModel: HomeViewModel = HomeViewModel()
) {
    val plants = plantsViewModel.getPlants();

    Surface(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxSize()
    ) {
        LazyColumn {
            items(plants) {
                Row {
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
    PlantSlider();
}
