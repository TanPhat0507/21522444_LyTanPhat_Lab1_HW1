package com.firstapp.cv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firstapp.cv.ui.theme.CVTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CVTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CVScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// Dữ liệu CV
data class CV(
    val name: String,
    val email: String,
    val phone: String,
    val address: String, // Thêm địa chỉ
    val education: String,
    val experience: String,
    val skills: String
)

@Composable
fun CVScreen(modifier: Modifier = Modifier) {
    val myCV = CV(
        name = "Lý Tấn Phát",
        email = "lytanphat0507@email.com",
        phone = "0963718142",
        address = "Phước Kiển, Nhà Bè, Hồ Chí Minh", // Địa chỉ mới
        education = "Đại học UIT",
        experience = """
            **05/12/2023 - 01/01/2024**
            - Forecasting Stock Prices
            - Research using Linear Regression, SVR, KNN.
            - Implemented models in Jupyter Notebook.
            **05/05/2023 - 17/05/2023**
            - Data Analysis Project
            - Cleaned, visualized data in Jupyter Notebook.
            - Presented insights on business trends.
        """.trimIndent(),
        skills = "Android, SQL"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        contentAlignment = Alignment.Center // Căn giữa toàn bộ nội dung
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f) // Giới hạn chiều rộng để không quá sát mép
                .wrapContentHeight(), // Chỉ chiếm không gian cần thiết theo chiều dọc
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Cột bên trái (Ảnh + Thông tin cá nhân)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(16.dp))
                CVText(title = "Họ và tên", content = myCV.name)
                CVText(title = "Email", content = myCV.email)
                CVText(title = "Số điện thoại", content = myCV.phone)
                CVText(title = "Địa chỉ", content = myCV.address)
            }

            // Cột bên phải (Thông tin CV)
            Column(
                modifier = Modifier
                    .weight(2f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(20.dp)
            ) {
                CVText(title = "Học vấn", content = myCV.education)
                CVText(title = "Kinh nghiệm", content = myCV.experience, isMultiLine = true)
                CVText(title = "Kỹ năng", content = myCV.skills)
            }
        }
    }

}

@Composable
fun CVText(title: String, content: String, isMultiLine: Boolean = false) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = content,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = if (isMultiLine) Modifier.padding(top = 4.dp) else Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CVPreview() {
    CVTheme {
        CVScreen()
    }
}
