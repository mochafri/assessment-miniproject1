package com.muhammadafrizal0011.mypdam

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muhammadafrizal0011.mypdam.ui.theme.MyPDAMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyPDAMTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White,
                )
            )
        }
    ) { innerPadding ->
        ScreenContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun CustomerNumberInput(
    value: String,
    isError: Boolean,
    onValueChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = stringResource(id = R.string.customer_number),
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(stringResource(id = R.string.number_placeholder)) },
            isError = isError,
            supportingText = {
                if (isError) Text(stringResource(R.string.input_invalid))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                if (isError) {
                    Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BulanDropdown(
    selectedBulan: String,
    isError: Boolean,
    onBulanSelected: (String) -> Unit
) {
    val listBulan = listOf(
        "Januari", "Februari", "Maret", "April",
        "Mei", "Juni", "Juli", "Agustus",
        "September", "Oktober", "November", "Desember"
    )
    var expanded by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = stringResource(id = R.string.period),
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedBulan,
                onValueChange = {},
                readOnly = true,
                isError = isError,
                placeholder = { Text(stringResource(id = R.string.select_month)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                supportingText = {
                    if (isError) Text(stringResource(R.string.input_invalid))
                }
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .heightIn(max = 200.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                listBulan.forEach { bulan ->
                    DropdownMenuItem(
                        text = { Text(bulan) },
                        onClick = {
                            onBulanSelected(bulan)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TagihanDialog(
    customerNumber: String,
    month: String,
    onDismiss: () -> Unit,
    onBayar: () -> Unit
) {
    val customerName = "Ujang"
    val biayaAdmin = 2500
    val pemakaianAir = 25
    val tarifPerM3 = 3000
    val totalAir = pemakaianAir * tarifPerM3
    val totalBayar = totalAir + biayaAdmin

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Row (
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextButton(onClick = onDismiss) {
                    Text(text = stringResource(R.string.close)) // sebaiknya pakai string.xml
                }
                TextButton(onClick = onBayar) {
                    Text(text = stringResource(R.string.pay)) // sebaiknya pakai string.xml
                }
            }
        },
        title = {
            Text(text = stringResource(R.string.dialog_title))
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("${stringResource(R.string.customer_name)}: $customerName")
                Text("${stringResource(R.string.customer_number)}: $customerNumber")
                Text("${stringResource(R.string.month)}: $month")
                Text("${stringResource(R.string.water_usage)}: $pemakaianAir m³")
                Text("${stringResource(R.string.price_per_m3)}: Rp $tarifPerM3")
                Text("${stringResource(R.string.water_cost)}: Rp $totalAir")
                Text("${stringResource(R.string.admin_fee)}: Rp $biayaAdmin")
                HorizontalDivider()
                Text("${stringResource(R.string.total_bill)}: Rp $totalBayar")
            }
        },
        shape = RoundedCornerShape(12.dp)
    )

}

@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    var customerName by remember { mutableStateOf("") }
    var customerNumber by remember { mutableStateOf("") }
    var selectedMonth by remember { mutableStateOf("") }
    var costumerNumberError by remember { mutableStateOf(false) }
    var selectedMonthError by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CustomerNumberInput(
            value = customerNumber,
            isError = costumerNumberError,
            onValueChange = {
                customerNumber = it
                costumerNumberError = false
            }
        )

        BulanDropdown(
            selectedBulan = selectedMonth,
            isError = selectedMonthError,
            onBulanSelected = {
                selectedMonth = it
                selectedMonthError = false
            }
        )

        Button(
            onClick = {
                costumerNumberError = customerNumber.isBlank() || customerNumber == "0"
                selectedMonthError = selectedMonth.isBlank()

                if (!costumerNumberError && !selectedMonthError) {
                    showDialog = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.check_button),
                color = Color.White,
                modifier = Modifier.padding(10.dp),
                fontSize = 20.sp
            )
        }

        if (showDialog) {
            TagihanDialog(
                customerNumber = customerNumber,
                month = selectedMonth,
                onDismiss = { showDialog = false },
                onBayar = {
                    Toast.makeText(
                        context,
                        "Pembayaran berhasil untuk $customerName ($customerNumber) bulan $selectedMonth",
                        Toast.LENGTH_LONG
                    ).show()
                    showDialog = false
                }
            )
        }
    }
}






@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    MyPDAMTheme {
        MainScreen()
    }
}