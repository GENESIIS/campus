/**
 * Created by tharaka on 10/24/2016.
 */

//20161205 JH c7-higher-education-landing-page QA MX point 3: removed w3IncludeHTML(); as it is no longer using


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
