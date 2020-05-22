var giMenuDuration = 700;

// 전체 메뉴를 오른쪽으로 슬라이드하여서 보여준다.
function showMenu(){
    $('.sidebarMenu' ).css( { 'display' : 'block' } );
    $('.sidebarMenu' ).css( { 'left' : '-100%' } );
    $('.sidebarMenu' ).animate( { left: '0px' }, { duration: giMenuDuration } );
}

// 전체 메뉴를 왼쪽으로 슬라이드하여서 닫는다.
function hideMenu(){
    $('.sidebarMenu' ).animate( { left: '-100%' }, { duration: giMenuDuration, complete:function(){ $('.menu_bg' ).css( { 'display' : 'none' } ); } } );
}