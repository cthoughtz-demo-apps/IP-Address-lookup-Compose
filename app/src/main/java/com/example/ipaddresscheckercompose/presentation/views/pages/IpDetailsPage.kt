package com.example.ipaddresscheckercompose.presentation.views.pages

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.ipaddresscheckercompose.R
import com.example.ipaddresscheckercompose.domain.model.IpStackResponse
import com.example.ipaddresscheckercompose.presentation.util.IPResults
import com.example.ipaddresscheckercompose.presentation.viewmodel.IpViewModel

private const val TAG = "IpDetailsPage"

@Composable
fun IpDetailsPage(ipViewModel: IpViewModel) {
    val ipResult = ipViewModel.ipResultFlow.collectAsStateWithLifecycle()

    var updatedState by remember { mutableStateOf(IpStackResponse()) }


    GetData(ipResult, onShowData = {
        updatedState = it
    })

    ShowDataOnScreen(ipViewModel, updatedState)

}

@Composable
fun GetData(ipResult: State<IPResults<IpStackResponse>>, onShowData: (IpStackResponse) -> Unit) {
    when (val ipData = ipResult.value) {
        is IPResults.Failure -> {
            Log.d(TAG, "GetData: ${ipData.errorMessage}")
        }

        is IPResults.Loading -> CircularProgressIndicator()
        is IPResults.Success -> {
            Log.d(TAG, "GetData: ${ipData.data}")
            onShowData(ipData.data)
        }

        else -> Log.d(TAG, "GetData: Something Went Wrong")

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShowDataOnScreen(ipViewModel: IpViewModel, updatedState: IpStackResponse?) {

    var showData by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "IP Info", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.baby_blue),
                    titleContentColor = Color.White
                )
            )

        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = colorResource(id = R.color.baby_blue)),

            ) {
            SearchBarForIpAddress(ipViewModel)
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize()
            ) {

                Spacer(Modifier.height(5.dp))

                if (updatedState?.ip != null) {
                    showData = true
                } else {
                    showData = false
                }

                AnimatedVisibility(showData) {
                    val space = Modifier.height(2.dp)
                    val labelSpace = Modifier.height(25.dp)

                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        CustomLabel("General Information")
                        Spacer(space)
                        CustomTextCard(leftData = updatedState?.type, rightData = updatedState?.ip)
                        Spacer(space)
                        CustomTextCard(leftData = updatedState?.continentCode, rightData = updatedState?.continentName)
                        Spacer(space)
                        CustomTextCard(leftData = updatedState?.countryCode, rightData = updatedState?.countryName)
                        Spacer(space)
                        CustomTextCard(leftData = "Capital", rightData = updatedState?.location?.capital)
                        Spacer(space)
                        CustomTextCard(leftData = "Language", rightData = updatedState?.location?.languages?.first()?.native)
                        Spacer(space)
                        CustomTextCard(leftData = updatedState?.regionCode, rightData = updatedState?.regionName)
                        Spacer(space)
                        CustomTextCard(leftData = "City", rightData = updatedState?.city)
                        Spacer(space)
                        CustomTextCard(leftData = "Zip", rightData = updatedState?.zip)
                        Spacer(space)
                        CustomTextCard(leftData = "Calling Code", rightData = updatedState?.location?.callingCode)
                        Spacer(labelSpace)

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    ImageRequest.Builder(LocalContext.current)
                                        .data(updatedState?.location?.countryFlag)
                                        .decoderFactory(SvgDecoder.Factory())
                                        .apply {
                                            size(Size.ORIGINAL) // Maintain SVG aspect ratio
                                        }
                                        .build()
                                ),
                                contentDescription = "Flag Image",
                                modifier = Modifier.size(300.dp),
                                alignment = Alignment.Center
                            )
                        }
                        Spacer(labelSpace)

                        CustomLabel("Region")
                        Spacer(space)
                        CustomTextCard(
                            leftData = "Latitude",
                            rightData = updatedState?.latitude.toString()
                        )
                        Spacer(space)
                        CustomTextCard(
                            leftData = "Longtitude",
                            rightData = updatedState?.longitude.toString()
                        )
                        Spacer(space)
                        CustomTextCard(
                            leftData = "MSA (Metropolitan Statistical Area)",
                            rightData = updatedState?.msa
                        )
                        Spacer(space)
                        CustomTextCard(
                            leftData = "DMA (Designated Market Area)",
                            rightData = updatedState?.dma
                        )
                        Spacer(space)
                        CustomTextCard("Radius", rightData = updatedState?.radius.toString())
                        Spacer(labelSpace)

                        CustomLabel("Networking")
                        Spacer(space)
                        CustomTextCard(
                            leftData = "Ip Routing Type",
                            rightData = updatedState?.ipRoutingType.toString()
                        )
                        Spacer(space)
                        CustomTextCard(
                            leftData = "Connection Type",
                            rightData = updatedState?.connectionType.toString()
                        )
                        Spacer(labelSpace)
                    }
                }

                Log.d(TAG, "ShowDataOnScreen: IP Check ${updatedState?.ip}")
            }
        }
    }
}

@Composable
fun CustomLabel(label: String) {
    Text(
        text = label,
        fontSize = 30.sp,
        color = Color.White,
        fontWeight = FontWeight.Thin
    )
}

@Composable
fun CustomTextCard(leftData: String?, rightData: String?) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .background(color = Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = leftData ?: "N/A")
            Text(text = rightData ?: "NA")
        }
    }
}

@Composable
fun SearchBarForIpAddress(ipViewModel: IpViewModel) {

    var searchResults by remember { mutableStateOf("") }
    var context = LocalContext.current

    SearchBar(
        onSearch = { query ->
            searchResults = "You search for: $query"
            Log.d(TAG, "SearchBarForIpAddress: Query: $query")
            query?.let {
                ipViewModel.getIPDetails(
                    query,
                    getString(context, R.string.api_access_key)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    onSearch: (String) -> Unit
) {

    var searchQuery by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = { Text(text = "Search IP4...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(searchQuery)
                focusManager.clearFocus()
            }
        ),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            containerColor = Color.White
        )
    )
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewIpDetailsPage() {
    IpDetailsPage(ipViewModel = hiltViewModel<IpViewModel>())
}