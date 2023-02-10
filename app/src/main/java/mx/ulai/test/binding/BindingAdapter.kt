package mx.ulai.test.binding

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import mx.ulai.test.R
import mx.ulai.test.model.VariantsColor
import mx.ulai.test.util.setImageGlide

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("switchVisible")
    fun switchVisible(view: View, show: Boolean){
        view.visibility = if(show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: ImageView, @Nullable url: String?){
        url?.let {
            view.setImageGlide(it)
        }
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("setDoubleToText")
    fun setDoubleToText(view: TextView, number: Double){
        view.text = "$$number"
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("setDoubleToTextsStrikeThrough")
    fun setDoubleToTextsStrikeThrough(view: TextView, number: Double){
        view.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        view.text = "$$number"
    }

    @JvmStatic
    @BindingAdapter("setColorsVariant")
    fun setColorsVariant(view: LinearLayout, variantColor: List<VariantsColor>?){
        variantColor?.forEach {
            if (it.colorHex.isNotEmpty()){
                val img = ImageView(view.context)
                img.setImageResource(R.drawable.circle)
                val color: Int = Color.parseColor(it.colorHex)
                img.setColorFilter(color)
                view.addView(img)
            }
        }

    }
}