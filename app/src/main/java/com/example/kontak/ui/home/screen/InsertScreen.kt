package com.example.kontak.ui.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kontak.navigation.DestinasiNavigasi
import com.example.kontak.ui.TopAppBarKontak
import com.example.kontak.ui.home.viewmodel.InsertUiEvent
import com.example.kontak.ui.home.viewmodel.InsertUiState
import com.example.kontak.ui.home.viewmodel.InsertViewModel
import com.example.kontak.ui.home.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiEntry : DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = "Entry Siswa"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryKontakScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBarKontak(
                title = DestinasiEntry.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryKontakBody(
            insertUiState = viewModel.insertKontakState,
            onKontakValueChange = viewModel::updateInsertKontakState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertKontak()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputSiswa(
    insertUiEvent: InsertUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiEvent.nama,
            onValueChange ={onValueChange(insertUiEvent.copy(nama = it))},
            label = { Text("Nama") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = insertUiEvent.alamat,
            onValueChange ={onValueChange(insertUiEvent.copy(alamat = it))},
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = insertUiEvent.nohp,
            onValueChange ={onValueChange(insertUiEvent.copy(nohp = it))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Telepon") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        if (enabled) {
            Text(
                text = "Isi semua data",
                modifier = Modifier.padding(start = 12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(bottom = 12.dp)
        )
    }
}

@Composable
fun EntryKontakBody(
    insertUiState: InsertUiState,
    onKontakValueChange: (InsertUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputSiswa(
            insertUiEvent = insertUiState.insertUiEvent,
            onValueChange = onKontakValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}