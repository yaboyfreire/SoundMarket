package pt.iade.ei.martim.rodrigo.SoundMarket.Components

import android.media.audiofx.AudioEffect.Descriptor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

//row aligns horizontally and column aligns vertically
//modifier allows to change properties related to margins and other stuff of the layout
@Composable
fun RatedContentListItem(
    title: String,
    description: String,
    rating:Float,
    numReviews:Int,
){

    Column{
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Text(
            text = description
        )
        Row{
            Text(
                text="${rating.roundToInt()} stars",
            )
            Text(
                text ="$numReviews reviews",
                modifier = Modifier.padding(5.dp,0.dp,5.dp,0.dp)
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun RatedContentListItemPreview(){
    RatedContentListItem(
        title="Everyone Hates Chris",
        description="Comedy following an awkward young African-American teenager (based on a young version of comedian Chris Rock) as he attempts to survive with his family and his all-white school in 1980's Brooklyn, New York.",
        rating=4.6f, numReviews = 5
    )
}