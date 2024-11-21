package tn.esprit.colormixer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import tn.esprit.colormixer.databinding.ActivityAnswerBinding

class AnswerActivity : AppCompatActivity() {

    //TODO 12 Add lateint var for binding
   private lateinit var binding: ActivityAnswerBinding

    private var correctColor = "NONE"
    private var name = "NONE"
    private var color1 = "NONE"
    private var color2 = "NONE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO 13 Bind the view and implement setContentView()
        binding = ActivityAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO 14 Change the value of correctColor / name / color1 / color2 with the DATA from the INTENT
        name = intent.getStringExtra("NAME") ?: "NONE"
        color1 = intent.getStringExtra("COLOR1") ?: "NONE"
        color2 = intent.getStringExtra("COLOR2") ?: "NONE"
        correctColor = intent.getStringExtra("MIXED_COLOR") ?: "NONE"
        correctColor = when {
            (color1 == "RED" && color2 == "BLUE") || (color1 == "BLUE" && color2 == "RED") -> "PURPLE"

            (color1 == "RED" && color2 == "YELLOW") || (color1 == "YELLOW" && color2 == "RED") -> "ORANGE"
            (color1 == "BLUE" && color2 == "YELLOW") || (color1 == "YELLOW" && color2 == "BLUE") -> "GREEN"
            else -> "NONE"
        }

        //TODO 15 Change the txtChoosed with : "You chose $color1 and $color2"
        binding.txtChoosed.text="You chose $color1 and $color2"
        //TODO 16 Implement setOnClickListener on the btnSubmit and call checkAnswer()
        // You must check if only one radio button is selected the navigate to the ResultActivity with the data name and RESULT (FAILED/SUCCESS)

        binding.btnSubmit.setOnClickListener {
            if (binding.rbPurple.isChecked || binding.rbGreen.isChecked || binding.rbOrange.isChecked) {
                val result = if (checkAnswer()) "SUCCESS" else "FAILED"
                val intent = android.content.Intent(this, ResultActivity::class.java).apply {
                    putExtra("NAME", name)
                    putExtra("RESULT", result)
                }
                startActivity(intent)
            }
        }

    }

    private fun checkAnswer(): Boolean{

        //TODO 17 Check if the answer of the chosen color is correct
        val selectedColor = binding.radioGroup.checkedRadioButtonId

        if (selectedColor == -1 ){
            Snackbar.make(binding.contextView, "You must select a color !", Snackbar.LENGTH_SHORT).show()
            return false

        }
        return true
    }
}