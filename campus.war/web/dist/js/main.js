/**
 * Created by tharaka on 10/24/2016.
 */

// w3IncludeHTML();

// Loading landing image slider
$(function() {
    $(".rslides").responsiveSlides();
});


// SlideToggle Controller
$(document).ready(function () {
    $(".flip").click(function () {
        $(this).next('.slideable').slideToggle("slow");
    });
});