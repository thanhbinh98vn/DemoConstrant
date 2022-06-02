package com.example.democonstrantlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.democonstrantlayout.ui.theme.DemoConstrantLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoConstrantLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Movie()
                }
            }
        }
    }
}

@Preview
@Composable
fun Movie() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (menuBottom, coverImage, titleText, genreText, ratingText, castContainer, castImage1, castImage2, castImage3, castImage4, destText) = createRefs()
        Icon(imageVector = Icons.Default.Menu, contentDescription = null,
            modifier = Modifier.constrainAs(menuBottom) {
                top.linkTo(parent.top, 16.dp)
                start.linkTo(parent.start, 16.dp)
            })

        val endGuideline = createGuidelineFromStart(.4f)

        Image(
            painter = painterResource(id = R.drawable.ironman),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(coverImage) {
                    start.linkTo(parent.start, 24.dp)
                    top.linkTo(menuBottom.bottom, 16.dp)
                    end.linkTo(endGuideline, 16.dp)
                    width = Dimension.fillToConstraints
                }
                .aspectRatio(2f / 3f)
        )

        Text(text = "End Game", style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.constrainAs(titleText) {
                top.linkTo(menuBottom.bottom, 8.dp)
                start.linkTo(coverImage.end, 16.dp)
            }
        )

        Text(text = "Action | 3h 10m",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.constrainAs(genreText) {
                top.linkTo(titleText.bottom)
                start.linkTo(coverImage.end, 16.dp)
            }
        )

        Text(text = "IMDb 7.1 /10", fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(ratingText) {
                top.linkTo(genreText.bottom)
                start.linkTo(coverImage.end, 16.dp)
            })

        ConstraintLayout(
            modifier = Modifier.constrainAs(castContainer) {
                bottom.linkTo(coverImage.bottom)
                start.linkTo(coverImage.end, 16.dp)
                end.linkTo(parent.end, 16.dp)
                width = Dimension.fillToConstraints
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.cast1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(50.dp)
                    .constrainAs(castImage1) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(coverImage.end, 16.dp)
                    }
                    .aspectRatio(1f)
            )

            Image(
                painter = painterResource(id = R.drawable.cast2),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(50.dp)
                    .constrainAs(castImage2) {
                        top.linkTo(castImage1.top)
                        bottom.linkTo(castImage1.bottom)
                        start.linkTo(castImage1.end, 5.dp)
                    }
                    .aspectRatio(1f)
            )

            Image(
                painter = painterResource(id = R.drawable.cast3),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(50.dp)
                    .constrainAs(castImage3) {
                        top.linkTo(castImage2.top)
                        bottom.linkTo(castImage2.bottom)
                        start.linkTo(castImage2.end, 5.dp)
                    }
                    .aspectRatio(1f)
            )

            Box(modifier = Modifier
                .height(50.dp)
                .background(Color.Gray)
                .constrainAs(castImage4) {
                    top.linkTo(castImage3.top)
                    bottom.linkTo(castImage3.bottom)
                    start.linkTo(castImage3.end, 5.dp)
                }
                .aspectRatio(1f)
            ) {
                Text(
                    text = "+10",
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .align(
                            Alignment.Center
                        ),
                    color = Color.Black
                )
            }

            createHorizontalChain(
                castImage1,
                castImage2,
                castImage3,
                castImage4,
                chainStyle = ChainStyle.SpreadInside
            )
        }
        val barrier = createBottomBarrier(coverImage, castContainer)
        Text(text = stringResource(id = R.string.description),
            color = Color.Gray,
            textAlign = TextAlign.Justify,
            fontSize = 16.sp,
            modifier = Modifier.constrainAs(destText) {
                top.linkTo(barrier, 36.dp)
                start.linkTo(parent.start, 24.dp)
                end.linkTo(parent.end, 24.dp)
                width = Dimension.preferredWrapContent
            })
    }


}

