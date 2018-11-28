package ru.rma.apps.google.search.top.google.search.ui.views

import android.widget.ImageButton
import com.google.android.material.textfield.TextInputEditText
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import ru.rma.apps.google.search.top.R

@RunWith(RobolectricTestRunner::class)
class GoogleSearchActivityTests {

    private val activity = Robolectric.setupActivity(GoogleSearchActivity::class.java)

    @Test
    fun activity_requireViews() {
        activity.requireViewById<TextInputEditText>(R.id.inputTextSearch)
        activity.requireViewById<ImageButton>(R.id.buttonSearch)
    }
}