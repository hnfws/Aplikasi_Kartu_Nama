package id.ac.stmi.aplikasi_kartu_nama

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileAcivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile_acivity)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = intent.getStringExtra("EXTRA_NAME") ?: ""
        val email = intent.getStringExtra("EXTRA_EMAIL") ?: ""
        val phone = intent.getStringExtra("EXTRA_PHONE") ?: ""
        val portfolio = intent.getStringExtra("EXTRA_PORTFOLIO") ?: ""

        val tvName = findViewById<TextView>(R.id.tvDetailName)
        val tvEmail = findViewById<TextView>(R.id.tvDetailEmail)
        val tvPhone = findViewById<TextView>(R.id.tvDetailPhone)
        val tvWeb = findViewById<TextView>(R.id.tvDetailWeb)
        val ivBack = findViewById<ImageView>(R.id.ivBack)

        tvName.text = name
        tvEmail.text = email
        tvPhone.text = phone
        tvWeb.text = portfolio

        ivBack?.setOnClickListener { finish() }

        val btnWebsite = findViewById<Button>(R.id.buttonKunjungiWebsite)
        btnWebsite.setOnClickListener {
            var url = portfolio
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://$url"
            }
            val intentWebsite = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intentWebsite)
        }

        val btnHubungi = findViewById<Button>(R.id.buttonHubungiSaya)
        btnHubungi.setOnClickListener {
            val intentDial = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phone")
            }
            startActivity(intentDial)
        }

        val btnBagikan = findViewById<Button>(R.id.buttonBagikanKartu)
        btnBagikan.setOnClickListener {
            val shareContent = """
                Halo, saya $name
                
                Email: $email
                WA/HP: $phone
                Portofolio: $portfolio
            """.trimIndent()

            val intentShare = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareContent)
            }
            startActivity(Intent.createChooser(intentShare, "Bagikan kartu nama via"))
        }
    }
}