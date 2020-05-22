const DURATION = 400;
const BOUNCE_DURATION = 400;
const constrain = document.body.querySelector('#js-constrain');
const X_SHIFT = 300;

const rectShadow = new mojs.Shape({
    shape:      'rect',
    fill:       'rgba(0,0,0, .2)',
    parent:     constrain,
    top:        '100%',
    left:       '23%',
    radius:     2,
    radiusX:    20,
    x:          21,
    rx:         3,
    opacity:    { 1 : .25 },
    scaleX:     { 1 : .75 },
    origin:     '0 50%',
    easing:     'cubic.out',
    delay:      200,
    duration:   250,
    // isShowStart: true,
    isShowEnd:    false,
    isForce3d:    true
}).then({
    opacity: { to: 1, easing: 'bounce.out' },
    scaleX: { to: 1, easing: 'bounce.out' },
    duration: BOUNCE_DURATION
});

const {approximate} = mojs.easing;

const easeOutUpper = (p) => {
    return mojs.easing.cubic.out(p) - p;
}

const easeOut = mojs.easing.mix(
    {to: .5, value: .375}, { to: 1, value: easeOutUpper } );

const SQUASH_SIZE = 1.5;

const scaleCurve = approximate((p) => {
    return 1 + SQUASH_SIZE*easeOut(p);
});

const nScaleCurve = approximate((p) => {
    return 1 - SQUASH_SIZE*easeOut(p);
});

const rect = new mojs.Shape({
    parent:   constrain,
    shape:    'rect',
    rx:       3,
    fill:     '#29363B',
    left:     '22%',
    top:      '100%',
    radius:   20,
    y:        -20,
    x:         22,
    scaleX:   { 1: 1, curve: scaleCurve },
    scaleY:   { 1: 1, curve: nScaleCurve },
    origin:   '0 100%',
    duration: 200,
    isForce3d:  true
}).then({
    angle: { to: -45, easing: 'cubic.out' },
    duration: 250,
}).then({
    angle: { to: 0, easing: 'bounce.out' },
    duration: BOUNCE_DURATION
});

const dustOpts = {
    parent:   constrain,
    radius:   20,
    fill:     'white',
    radius:   'rand(3, 15)',
    left:     '20%',
    top:      '100%',
    scale:    { 1: 0 },
    x:        { 0: 70 },
    y:        { 0: -20 },
    isForce3d: true,
    swirlSize: 50,
    swirlFrequency: 1,
    isTimelineLess: true,
}

const dust = new mojs.Timeline;

for (var i = 0; i < 7; i++) {
    dust.add( new mojs.ShapeSwirl({
            ...dustOpts,
            delay:  i*45
        })
    )
}

const dustTrail = new mojs.Timeline;

for (var i = 0; i < 2; i++) {

    let direction = ( i % 2 === 0 ) ? 1 : -1;
    let pathScale = ( i % 2 === 0 ) ? 1 : .75;

    dustTrail.add( new mojs.ShapeSwirl({
            ...dustOpts,
            radius:   'rand(10, 15)',
            delay:  200 + i*45,
            direction,
            pathScale,
            swirlSize: 10,
            swirlFrequency: 3,
        })
    )
}


mojs.h.force3d( constrain );
const constrainTween = new mojs.Tween({
    onUpdate: (p) =>  {
        constrain.style[ 'transform' ] =
            `translateX(${X_SHIFT*(1-p)}px)`;
    },
    duration: DURATION
});

const timeline = new mojs.Timeline({ delay: 500, speed: .5 });

timeline
    .add(
        constrainTween, rect, rectShadow,
        dust, dustTrail
    );

new MojsPlayer({ add: timeline, isPlaying: true, isRepeat: false, isHidden: true });