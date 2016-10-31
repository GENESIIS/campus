/*!
 * Contact Buttons Plugin Demo 0.1.0
 * 20161025 AS c12-social-media-share-panel-as side share panel and top share panel button and js created
 * 20161025 AS c12-social-media-share-panel-as share_panale name rename to share_panel.js
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
    'linkedin':   { class: 'linkedin', use: true, link: 'http://www.linkedin.com/shareArticle?mini=true&amp;url=' + encodeURIComponent(location.href), extras: 'target="_top "' },
    'google':     { class: 'gplus',    use: true, link: 'https://plus.google.com/share?url={' +encodeURIComponent(location.href), extras: 'target="_top "}'},
    'twitter':   { class: 'twitter',   use: true, link: 'https://twitter.com/share?url=' + encodeURIComponent(location.href), extras: 'target="_top "' },
    'phone':      { class: 'phone separated',    use: true, link: '+94 11 2329 868' },
    'email':      { class: 'email',    use: true, link: ' info@genesiis.com' }
  }
});