$('.select-btn ul li.option').click(function() {
    $(this).siblings().children().remove();
    var a = $(this).siblings().toggle();
    console.log($(a).is(":visible"));
});