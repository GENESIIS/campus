/*!
 * Contact Buttons Plugin Demo 0.1.0
 * 20161025 AS c12-social-media-share-panale-as side share panale and top share panale button and js created
 */
 
// Google Fonts
WebFontConfig = {
  google: { families: [ 'Lato:400,700,300:latin' ] }
};
(function() {
  var wf = document.createElement('script');
  wf.src = ('https:' == document.location.protocol ? 'https' : 'http') +
    '://ajax.googleapis.com/ajax/libs/webfont/1/webfont.js';
  wf.type = 'text/javascript';
  wf.async = 'true';
  var s = document.getElementsByTagName('script')[0];
  s.parentNode.insertBefore(wf, s);
})();

// Initialize Share-Buttons
$.contactButtons({
  effect  : 'slide-on-scroll',
  buttons : {
    'facebook':   { class: 'facebook', use: true, link: 'http://www.facebook.com/share.php?u=' + encodeURIComponent(location.href), extras: 'target="_top "' },
    'linkedin':   { class: 'linkedin', use: true, link: 'https://www.linkedin.com/share?=' + encodeURIComponent(location.href), extras: 'target="_top "' },
    'google':     { class: 'gplus',    use: true, link: 'https://plus.google.com/share?url={' +encodeURIComponent(location.href), extras: 'target="_top "}'},
    'twitter':   { class: 'twitter',   use: true, link: 'https://twitter.com/intent/tweet?original_referer=' + encodeURIComponent(location.href), extras: 'target="_top "' },
    'phone':      { class: 'phone separated',    use: true, link: '+000' },
    'email':      { class: 'email',    use: true, link: 'test@web.com' }
  }
});