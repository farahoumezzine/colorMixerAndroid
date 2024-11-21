package tn.esprit.colormixer

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import tn.esprit.colormixer.databinding.ActivityQuestionBinding

//TODO 2 Add string constant val here for RED / BLUE / YELLOW / PURPLE / GREEN / ORANGE
 const val RED = "RED"
 const val BLUE = "BLUE"
 const val YELLOW = "YELLOW"
 const val PURPLE = "PURPLE"
 const val GREEN = "GREEN"
 const val ORANGE = "ORANGE"


//TODO 3 Add string constant val here for NAME / MIXED_COLOR / COLOR1 / COLOR2 / RESULT / SUCCESS / FAILED
const val NAME = "NAME"
 const val MIXED_COLOR = "MIXED_COLOR"
 const val COLOR1 = "COLOR1"
 const val COLOR2 = "COLOR2"
const val RESULT = "RESULT"
 const val SUCCESS = "SUCCESS"
 const val FAILED = "FAILED"



class QuestionActivity : AppCompatActivity() {

    //TODO 4 Add lateint var for binding
    private lateinit var binding: ActivityQuestionBinding

    //TODO 5 Add var for colorMixed / color1 / color2 / name
    private var colorMixed = ""
    private var color1 = ""
    private var color2 = ""
    private var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO 6 Bind the view and implement setContentView()
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO 7 Implement setOnClickListener on the button Mix and call mixColor()

        binding.btnMix.setOnClickListener {
            mixColor()
        }
    }

    private fun mixColor(){

        //TODO 8 Check if the input for FullName. IF it's empty show a snackbar with the message : You must enter your name !
        if (binding.tfFullName.text.toString().isEmpty()){
            Snackbar.make(binding.contextView, "You must enter your name !", Snackbar.LENGTH_SHORT).show()
            return
        }

        //TODO 9 Check if Only 2 colors are selected then change the value of  colorMixed / color1 / color2
      val selectedColors = ArrayList<String>()
        if (binding.cbBlue.isChecked) selectedColors.add(BLUE)
        if (binding.cbYellow.isChecked) selectedColors.add(YELLOW)
        if (binding.cbRed.isChecked) selectedColors.add(RED)

        if (selectedColors.size != 2) {
            Snackbar.make(binding.contextView, "You must select 2 colors !", Snackbar.LENGTH_SHORT).show()
        }
        color1=selectedColors[0]
        color2=selectedColors[1]
        colorMixed= when{
            (color1 == RED && color2 == BLUE) || (color1 == BLUE && color2 == RED) -> PURPLE
            (color1 == RED && color2 == YELLOW) || (color1 == YELLOW && color2 == RED) -> ORANGE
            (color1 == BLUE && color2 == YELLOW) || (color1 == YELLOW && color2 == BLUE) -> GREEN
            else -> ""
        }

        //TODO 10 Change the value of name with the input
        name=binding.tfFullName.text.toString()

        //TODO 11 Create an Intent to AnswerActivity and add all of the values name / colorMixed / color1 / color2 Then start the Activity
        val intent = android.content.Intent(this, AnswerActivity::class.java).apply{
            putExtra(NAME, name)
            putExtra(MIXED_COLOR, colorMixed)
            putExtra(COLOR1, color1)
            putExtra(COLOR2, color2)

        }
        startActivity(intent)

    }
}