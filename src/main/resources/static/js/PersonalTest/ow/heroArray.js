const WIDOWMAKER = ['WIDOWMAKER', 20, 0, '위도우메이커' ,'한 방이면 충분해.',
    '위도우메이커는 아무런 자비 없이 타깃을 처리하는 더할 나위 없는 암살자입니다.' +
    ' 탈론에 소속되어 있지만 그 단체에 소속감을 느끼거나 조직원들과 교류를 하는 일이 없고 오히려 타인과의 교류를 꺼립니다. ' +
    '철저한 개인주의자이며 임무를 완수하는 순간의 만족감을 제외하면 아무런 감정도 느끼지 못하는 결과주의자입니다. ' +
    '어쩌면 이런 점이 당신과 위도우 메이커의 공통점일지도 모르겠어요!'];

const ROADHOG = ['ROADHOG', 20, 4, '로드호그', '나는 재앙을 불러온다.',
    '로드호그는 잔혹하고 파괴적인 무자비한 살인마입니다. ' +
    '옴닉에게 고향을 뺏긴 로드호그는 가면을 뒤집어쓴 채 고물 바이크를 타고 방사능에 오염된 도로 위를 달리며 인간성이 조금씩 사라져 지금의 잔혹한 살인마 로드호그가 되었습니다. ' +
    '정크랫에게 고용되어 함께 활동하지만, 그 밖에 가까이 지내는 관계가 없는 개인주의자이며 남의 일에 신경을 쓰지 않는 성격입니다. ' +
    '어쩌면 이런 점이 당신과 로드호그의 공통점일지도 모르겠어요!'];

const REAPER = ['REAPER', 19, 1, '리퍼', '죽음이 너희 곁을 걷는다.',
    '리퍼는 탈론의 위험한 용병이자 암살자입니다. ' +
    '한때 블랙 워치의 사령관이었던 가브리엘 레예스이었지만 실패한 유전자 개조 실험으로 인해 지금의 모습이 되었습니다. ' +
    '임무를 수행함에 있어 잔혹한 편이며 항상 죽음과 함께합니다. ' +
    '블랙 워치 때에는 블랙 워치 팀원들과 교류하며 팀을 이끌었으며, ' +
    '리퍼가 된 이후에도 탈론 멤버들과 교류하는 것을 꺼리는 편이지만 임무를 위해서 탈론의 멤버들과 협력하는 편입니다. ' +
    '개인주의자 성향을 가졌지만 협력할 때는 협력하는 점이 당신과 리퍼의 공통점일지도 모르겠어요! '];

const SIGMA = ['SIGMA', 18, 2, '시그마', '우주는 쉽게 이해할 수 있는 존재가 아니다.',
    '시그마는 뛰어난 천체 물리학자입니다. 불의의 사고로 인해 중력의 힘을 통제할 수 있습니다. ' +
    '탈론이 배후에서 그와 그의 연구를 이용하고 있으나 그 사실을 모른 채 홀로 활동합니다. ' +
    '탈론 또는 오버워치 요원들과도 상호 교류가 없으며 어떠한 영웅과도 교류가 없는개인주의자 입니다. ' +
    '어쩌면 이런 점이 당신과 시그마의 공통점일지도 모르겠어요!'];

const MOIRA = ['MOIRA', 17, 0, '모이라', '과학이 진실을 밝혀줄 거야.',
    '모이라는 논란의 중심에 있는 탈론 과학자입니다. ' +
    '과학의 진보를 욕망하며 그를 위해 윤리적인 부분이나 타인의 고통은 고려하지 않습니다. ' +
    '리퍼를 지금의 모습으로 만들었으며 과거 블랙 워치 소속이었습니다. ' +
    '지금은 탈론에 소속되어 있으며 오버워치 멤버들과 탈론 멤버들에게 빈정거리는 말이나 ' +
    '트라우마를 건드리는 말을 주로 건네곤 합니다. 당신이 미친 과학자는 아니겠지만 이성적이며 개인주의자인 점은 ' +
    '살짝 닮았을지도 모르겠네요.'];

const SYMMETRA = ['SYMMETRA', 16, 0, '시메트라', '인류의 진정한 적은 무질서예요.',
    '시메트라는 인도 출신의 비슈카르 코퍼레이션에 소속된 최고의 광축가 입니다. \n' +
    '자신의 행동이 사회의 더 큰 선을 위한 것이라 믿고 있지만, 자신이 바라는 통제와 질서가 진정 인류에게 최선인지 의심하기도 합니다. \n' +
    '자유보다 질서와 통제가 가치 있다고 믿는 신념 때문에 자유를 더 큰 가치로 여기는 루시우와 다툼이 있습니다. \n' +
    '다른 영웅들과의 교류가 없는 것은 아니나 말수가 적고 다소 개인주의자 경향이 있습니다.\n' +
    '자신이 믿는 신념에 따라 이성적으로 행동하는 시메트라의 이런 점이 당신과 닮았을지도 모르겠네요!'];

const HANZO = ['HANZO', 16, 2, '한조', '죽음에는 명예가 따르고, 명예에는 구원이 따른다.',
    '한조는 시마다 가문의 용을 다루는 전사입니다.\n' +
    '가문을 위해서 동생 겐지를 죽였을 정도로 가문과 명예를 중요시하며 이성적인 면을 보여줍니다.\n' +
    '하지만 겐지를 죽인 것에 대하여 큰 죄책감을 느껴 아버지의 유산을 거부하고 홀로 용병이자 암살자로 살아갈 정도로 감정적인 면 또한 있습니다.\n' +
    '이성과 감정을 넘나드는 이런 점이 당신과 한조와의 공통점일 수도 있겠네요.'];

const ZENYATTA = ['ZENYATTA', 15, 20, '젠야타', '진정한 자아엔 형체가 없는 법'
    , '젠야타는 인간과 기계 사이의 갈등을 가르침이 아닌 교감으로 해소하고자 하는 방랑자입니다.'
    + '자신의 신념을 위해서라면 스승과 동료들을 뒤로 하고 혼자 활동하는 것도 마다하지 않는 성격을 가지고 있습니다.'
    + '평화를 찾아 세계를 떠도는 젠야타는 무고한 자들을 위해 도움의 손길을 건내는걸 마다하지 않을 정도로 온화한 인성의 소유자이네요.'
    + ' 어쩌면 이런 점이 당신과 젠야타의 공통점일지도 모르겠어요!'];

const BASTION = ['BASTION', 14, 10, '바스티온', '쀼삡',
    '바스티온은 살아남은 옴닉 군인 입니다. \n' +
    '옴닉사태 최후의 전투에서 크게 손상되어 10년 넘게 잊힌 채 자연에 뒤덮여 있었던 바스티온은 어느 날 갑작스레 재가동 되었습니다. \n' +
    '다시 깨어난 바스티온은 전투 프로그램 대신 자연과 동물에 대한 깊은 호기심을 가지고 자연을 탐험하며 자신의 존재 이유를 찾기 위한 여행을 하고 있습니다. \n' +
    '위험을 감지하면 핵심 전투 프로그램이 실행되어 사람들과 충돌을 일으키기에 홀로 다니는 것을 선호하지만 가니메데스라는 조류 친구가 있습니다.\n' +
    '사람들과의 충돌을 꺼리지만 세상 속에 녹아들고싶어하는 바스티온의 이런 점이 당신과 닮았을지도 모르겠네요.'];

const DOOMFIST = ['DOOMFIST', 13, 0, '둠피스트', '갈등만이 우리를 진화시킨다.',
    '나이지리아 출생인 본명 아칸데 오군디무, 즉 둠피스트는 덕망 높은 가문에서 가업을 이을 후계자로 태어나 옴닉사태 때 한쪽 팔을 잃었습니다.\n' +
    '무술과 격투를 사랑하여 갈고닦아왔으며, 용병으로써 여기저기 교활한 활약을 펼칩니다. \n' +
    '그로 인해 탈론에 눈에 들었고 탈론에 들어가 높은 지위에 올랐습니다.\n' +
    '\'인류는 갈등을 통해 강해진다\'라는 신념으로 인해 전 세계를 뒤덮을 분쟁을 준비하고 있습니다.\n' +
    '지휘관의 재능을 타고난 만큼 사람들과 교류하는 것을 어려워하지 않는 이성주의자입니다. 당신도 그런가요?'];

const SOLDIER76 = ['SOLDIER76', 12, 2, '솔저 76', '이 시간부로 우린 모두 군인이다.',
    '솔져는 은퇴한 (전)오버워치 사령관입니다. \n' +
    '군인 강화 프로그램을 통해 일반 군인을 초월하는 능력을 갖추었습니다. \n' +
    '오버워치를 무너뜨린 음모를 파헤치며 개인적인 활동을 하고있지만 전 오버워치 요원들을 아끼는 모습도 보여줍니다. \n' +
    '타고난 군인인 그는 원칙을 고수하는 보수적인 모습을 보이며 개인으로 활동하면서도 자신의 바운더리 안에 들어온 사람들에게는 애정을 아끼지 않는 사람이네요.\n' +
    '이런 점이 당신과 닮았을지도 모르겠어요!\n'];

const MCREE = ['MCREE', 12, 12, '맥크리'];

const SOMBRA = ['SOMBRA', 12, 4, '솜브라'];

const ZARYA = ['ZARYA', 11, 6, '자리야'];

const MEI = ['MEI', 10, 20, '메이'];

const WREKINGBALL = ['WREKINGBALL', 10, 4, '레킹볼'];

const PHARAH = ['PHARAH', 10, 2, '파라'];

const TORBJORN = ['TORBJORN', 9, 2, '토르비욘'];

const ASH = ['ASH', 9, 14, '애쉬'];

const ANA = ['ANA', 8, 6, '아나'];

const GENJI = ['GENJI', 8, 16, '겐지'];

const MERCY = ['MERCY', 7, 8, '메르시'];

const JUNKRAT = ['JUNKRAT', 6, 16, '정크랫'];

const WINSTON = ['WINSTON', 6, 14, '윈스턴'];

const ECHO = ['ECHO', 5, 8, '에코'];

const ORISA = ['ORISA', 5, 6, '오리사'];

const BRIGITTE = ['BRIGITTE', 4, 18, '브리기테'];

const BAPTISTE = ['BAPTISTE', 3, 14, '바티스트'];

const DVA = ['DVA', 2, 8, '디바'];

const REINHARDT = ['REINHARDT', 1, 18, '라인하르트'];

const TRACER = ['TRACER', 0, 16, '트레이서'];

const LUCIO = ['LUCIO', 0, 20, '루시우'];

const HEROS = [];

HEROS.push(WIDOWMAKER, ROADHOG, REAPER, SIGMA, MOIRA, SYMMETRA, HANZO, ZENYATTA, BASTION, DOOMFIST, SOLDIER76, MCREE,
    SOMBRA, ZARYA, MEI, WREKINGBALL, PHARAH, TORBJORN, ASH, ANA, GENJI,
    MERCY, JUNKRAT, WINSTON, ECHO, ORISA, BRIGITTE, BAPTISTE, DVA, REINHARDT, TRACER, LUCIO);
