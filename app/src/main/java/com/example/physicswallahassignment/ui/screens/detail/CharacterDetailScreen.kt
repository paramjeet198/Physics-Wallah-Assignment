
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.physicswallahassignment.data.remote.model.CharacterDetailResponse
import com.example.physicswallahassignment.ui.components.ErrorState
import com.example.physicswallahassignment.ui.components.LoadingState
import com.example.physicswallahassignment.ui.screens.detail.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(viewModel: DetailViewModel = hiltViewModel()) {
    val detailState = viewModel.detailStateHolder.collectAsState()
    val character = detailState.value.data

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = character?.name ?: "Character Detail") },
//                backgroundColor = MaterialTheme.colorScheme.primary
            )
        }
    ) { contentPadding ->

        val screenHeightDp = with(LocalDensity.current) {
            (0.3f * LocalDensity.current.density).toDp() // 30% of the screen height
        }

        when {
            detailState.value.isLoading -> {
                LoadingState(contentPadding = contentPadding)
            }

            detailState.value.error != null -> {
                ErrorState(errorMessage = detailState.value.error, contentPadding = contentPadding)
            }

            character != null -> {
                CharacterDetail(contentPadding, character)
            }
        }
    }
}
@Composable
private fun CharacterDetail(
    contentPadding: PaddingValues,
    character: CharacterDetailResponse
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        // Use a Column wrapped in a ScrollableBox to handle large content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()) // Makes the column scrollable
        ) {
            // Image section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(bottom = 16.dp)
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
            }

            // Character details
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Name: ${character.name.orEmpty()}",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Status: ${character.status.orEmpty()}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Species: ${character.species.orEmpty()}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Type: ${character.type.orEmpty()}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Gender: ${character.gender.orEmpty()}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Origin: ${character.origin?.name.orEmpty()}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Location: ${character.location?.name.orEmpty()}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Episodes: ${character.episode?.size ?: 0}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Created: ${character.created.orEmpty()}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}

