/**
 * Created by tharaka on 2/20/2017.
 */
/**
 * 20170220 TR CAM-139: INIT ui-controller.js file to control all ui validations
 */

// 20170220 TR CAM-139: implement js to modal show and hidden event
// 20170220 TR CAM-139: added css to modal and body on modal show
// 20170220 TR CAM-139: removed that css from body on modal hidden

$( document ).ready(function() {
    var scrollPos = 0;

    $('.modal')
        .on('show.bs.modal', function (){
            scrollPos = $('body').scrollTop();
            $('body').css({
                overflow: 'hidden',
                position: 'fixed',
                top : -scrollPos
            });
            $('.modal').css({
                overflow: 'auto'
            });
        })
        .on('hide.bs.modal', function (){
            $('body').css({
                overflow: '',
                position: '',
                top: ''
            }).scrollTop(scrollPos);
        });

}); 