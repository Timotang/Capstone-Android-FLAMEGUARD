package com.bionsonluaguezosa.flameguard.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.bionsonluaguezosa.flameguard.CaptureFireActivity
import com.bionsonluaguezosa.flameguard.R

class ReportFragment : Fragment() {

    private lateinit var webView: WebView
    private lateinit var locationInput: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_report, container, false)

        // Initialize views
        webView = rootView.findViewById(R.id.map_webview)
        locationInput = rootView.findViewById(R.id.location_input)
        val nextButton: Button = rootView.findViewById(R.id.next_button)

        // Setup WebView
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        // Load the map URL
        val mapHtml = """
            <html>
                <body>
                    <iframe 
                        width="100%" 
                        height="100%" 
                        frameborder="0" 
                        style="border:0" 
                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3153.003373782617!2d144.95373631531886!3d-37.817209979751654!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x6ad65d43a7d0f8f5%3A0x1f1d93cfd9e0d7!2sGoogle!5e0!3m2!1sen!2sus!4v1603969811557!5m2!1sen!2sus" 
                        allowfullscreen>
                    </iframe>
                </body>
            </html>
        """.trimIndent()

        webView.loadData(mapHtml, "text/html", "UTF-8")

        // Next button click listener
        nextButton.setOnClickListener {
            // Navigate to FireStationActivity
            val intent = Intent(activity, CaptureFireActivity::class.java)
            startActivity(intent)
        }

        return rootView
    }
}
