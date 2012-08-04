var status0='';
var curfontsize=10;
var curlineheight=18;
function fontZoomA(){
  if(curfontsize>8){
    document.getElementById('article_content').style.fontSize=(--curfontsize)+'pt';
	document.getElementById('article_content').style.lineHeight=(--curlineheight)+'pt';
  }
}
function fontZoomB(){
  if(curfontsize<64){
    document.getElementById('article_content').style.fontSize=(++curfontsize)+'pt';
	document.getElementById('article_content').style.lineHeight=(++curlineheight)+'pt';
  }
}