import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.physicswallahassignment.common.formatApiDate
import com.example.physicswallahassignment.data.remote.model.CharacterDetailResponse
import com.example.physicswallahassignment.ui.components.ErrorState
import com.example.physicswallahassignment.ui.components.LoadingState
import com.example.physicswallahassignment.ui.screens.detail.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val detailState = viewModel.detailStateHolder.collectAsState()
    val character = detailState.value.data

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = character?.name ?: "Character Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                })
        }
    ) { contentPadding ->

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
                    .height(320.dp)
                    .padding(bottom = 2.dp)
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.small),
                    contentScale = ContentScale.Crop
                )
            }

            // Character details
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp),
                    color = MaterialTheme.colorScheme.background,
                    shape = MaterialTheme.shapes.medium,
                    tonalElevation = 2.dp
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = character.name.orEmpty(),
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        CharacterDetailItem(label = "Status", value = character.status ?: "-")
                        HorizontalDivider()
                        CharacterDetailItem(label = "Species", value = character.species ?: "-")
                        HorizontalDivider()
                        CharacterDetailItem(
                            label = "Type",
                            value = character.type?.takeIf { it.isNotEmpty() } ?: "-"
                        )
                        HorizontalDivider()
                        CharacterDetailItem(label = "Gender", value = character.gender ?: "-")
                        HorizontalDivider()
                        CharacterDetailItem(
                            label = "Origin",
                            value = character.origin?.name ?: "-"
                        )
                        HorizontalDivider()
                        CharacterDetailItem(
                            label = "Location",
                            value = character.location?.name ?: "-"
                        )
                        HorizontalDivider()
                        CharacterDetailItem(
                            label = "Episodes",
                            value = "${character.episode?.size ?: 0}"
                        )
                        HorizontalDivider()
                        CharacterDetailItem(label = "Created", value = character.created?.let {
                            formatApiDate(
                                it
                            )
                        }
                            ?: "-")

                    }
                }
            }
        }
    }
}


@Composable
private fun CharacterDetailItem(label: String, value: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

