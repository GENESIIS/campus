/**
 * Created by tharaka on 10/24/2016.
 */

//removed w3IncludeHTML() 

// SlideToggle Controller

$(document).ready(function () {
    $(".flip").click(function () {
        $(this).next('.slideable').slideToggle("slow");
    });
});
