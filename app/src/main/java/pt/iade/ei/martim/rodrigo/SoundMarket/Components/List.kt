package pt.iade.ei.martim.rodrigo.SoundMarket.Components

import android.media.audiofx.AudioEffect.Descriptor
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

//row aligns horizontally and column aligns vertically
//modifier allows to change properties related to margins and other stuff of the layout
@Composable
fun RatedContentListItem(
    author:String,
    title: String,
    description: String,
    rating:Float,
    numReviews:Int,
){

    Column{
        Text(
            text = ""
        )
        Spacer(
            modifier = Modifier
                .size(200.dp)
                .background(Color.LightGray)
                .align(Alignment.CenterHorizontally)

        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp


        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = author,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text=description,
            textAlign = TextAlign.Center

        )
    Row{
        Text(
            text="$rating stars",
            modifier = Modifier.padding(10.dp,10.dp,10.dp,10.dp)
        )

        Text(
            text ="$numReviews reviews",
            modifier = Modifier.padding(10.dp,10.dp,10.dp,10.dp)
        )
    }




    }
}




@Preview(showBackground = true)
@Composable
fun RatedContentListItemPreview(){
    RatedContentListItem(
        author="J. Cole",
        title="2014 Forest Hills Drive",
        description="2014 Forest Hills Drive is the third studio album by American rapper J. Cole, released on December 9, 2014, by ByStorm Entertainment, Columbia Records, Dreamville Records and Roc Nation.",
        rating=4.6f,
        numReviews = 5
    )
}