create extension if not exists "uuid-ossp";

create table if not exists book
(
    uuid    uuid    not null default uuid_generate_v4(),
    sort    varchar not null,
    type    varchar not null,
    image   varchar not null,
    content varchar not null,


    constraint beer_uuid_pk primary key (uuid),
    constraint beer_type_uk unique (type)
);

INSERT INTO public.book (uuid, sort, type, image, content)
VALUES ('9ed339f3-743c-4b7a-8140-d89a48bb4214', 'Эль', 'Пшеничное пиво',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/2013_Augustiner_Weissbier_Munich_pub.jpg/548px-2013_Augustiner_Weissbier_Munich_pub.jpg',
        e 'Пшеничное пиво (белое пиво, вайцен, вайсбир, нем. Weizenbier, Weissbier, нидерл. Witbier) — это пиво верхового брожения, которое варится с большой долей пшеницы по отношению к количеству ячменного солода. Два основных сорта — немецкий Weizenbier и бельгийский Witbier.

Другие типы включают ламбик (изготавливается с использованием диких дрожжей), Berliner Weisse (мутное, кислое пиво) и гозе (кислое, соленое пиво).');
INSERT INTO public.book (uuid, sort, type, image, content)
VALUES ('e49dff63-0463-4c5e-8b42-332586cc7935', 'Эль', 'Берлинское белое',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/BerlinerWeisse.jpg/548px-BerlinerWeisse.jpg',
        'Берлинер Вайссе (нем. Berliner Weiße) — это мутный, кислый стиль пива с содержанием алкоголя около 5 % по объёму. Это региональная разновидность пшеничного пива из Северной Германии, появившаяся как минимум в XVI веке. Оно может быть приготовлено из комбинации ячменного и пшеничного солода, при этом солод обжигается при очень низких температурах или даже сушится на воздухе, чтобы минимизировать образование цвета. Брожение происходит с помощью смеси дрожжей (Saccharomyces cerevisiae и Brettanomyces) и молочнокислых бактерий, что создает кисломолочный вкус — отличительную черту стиля Берлинер Вайссе');
INSERT INTO public.book (uuid, sort, type, image, content)
VALUES ('af8309f5-dbbf-440c-8dcf-0b67c229aa5f', 'Эль', 'Пейль-эль',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/THAT_Brewery_Bearfoot_Pale_Ale.jpg/390px-THAT_Brewery_Bearfoot_Pale_Ale.jpg',
        'Пейл-эль (англ. Pale Ale; в переводе с англ. — «бледный эль») — это обычно золотистый или янтарный сорт эля, который варится с использованием элевых дрожжей и преимущественно светлого солода. Впервые этот термин появился около 1703 года для пива, приготовленного из солода, высушенного с помощью высокоуглеродистого кокса, что привело к более светлому цвету по сравнению с другими сортами пива, популярными в то время. Различные методы пивоварения и количество хмеля привели к появлению целого ряда вкусов и крепостей в семействе бледных элей.');
INSERT INTO public.book (uuid, sort, type, image, content)
VALUES ('1e2d01e8-0880-490e-aaf2-61fd18778db4', 'Эль', 'Биттер',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/0/01/Ale_Bitter.jpg/526px-Ale_Bitter.jpg',
        'Биттер (Bitter, с англ. — «горький») — английский горький эль, сорт светлого эля. Хотя с английского и немецкого языков Bitter переводится как «горький», на самом деле этот эль имеет очень приятный вкус. Свое название он приобрел несколько веков назад, когда английские пивовары стали использовать хмель, придающий пиву горьковатый привкус. Типичный горький эль имеет цвет тёмной меди, хотя у некоторых специальных сортов оттенок варьируется от янтарного до бронзового. Характерная крепость — от 3 % до 7 %');
INSERT INTO public.book (uuid, sort, type, image, content)
VALUES ('933c4cab-98b4-4d6b-aa7e-e220b924a5f4', 'Эль', 'Дуббель',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Bornemdubbel.jpg/548px-Bornemdubbel.jpg',
        'Дуббель, или бельгийский дубль эль (англ. Belgian Dubbel Ale) — тёмно-красноватый, умеренно крепкий, солодовый бельгийский эль, подвид стиля "бельгийский крепкий эль". Эта категория включает в себя множество траппистских и аббатских сортов бельгийского пива, а также их имитаций в Европе и Северной Америке.');
INSERT INTO public.book (uuid, sort, type, image, content)
VALUES ('b9132578-b626-47a5-9dc2-f2a42cbda90d', 'Лагер', 'Пильзнер',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Pilznery.jpg/548px-Pilznery.jpg',
        'Пильзнер (от нем. Pilsner, букв. «пльзеньское») — наиболее распространённый на рынке вид пива низового брожения. Имеет характерный пивной аромат и мягкий вкус хмеля. Назван в честь богемского города Пльзень (нем. Pilsen), где был изобретён');
INSERT INTO public.book (uuid, sort, type, image, content)
VALUES ('01fe25f0-1fde-450e-8e57-7c4f4ed56270', 'Лагер', 'Бокбир',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/8/86/Johann_Wilhelm_Preyer_-_Münchner_Bockstilleben.jpg/440px-Johann_Wilhelm_Preyer_-_Münchner_Bockstilleben.jpg',
        e 'Бокбир (нем. Bockbier, Starkbier), сокращённо бок (Bock) — разновидность немецкого крепкого пива исключительно низового брожения с экстрактивностью начального сусла более 16 % и крепостью 6,3-7,2 %. В основном тëмное, за исключением майбока.

Хотя этимология пива бок не имеет ничего общего с козлом (а Bock по-немецки означает «козёл»), изображение этого животного часто помещают на этикетку. В Польше используется калька «козляк».');
INSERT INTO public.book (uuid, sort, type, image, content)
VALUES ('960b1123-8253-4f40-9fe4-379c3026285d', 'Лагер', 'Цвикельбир',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/Eichbaum_Kellerbier_2007.jpg/440px-Eichbaum_Kellerbier_2007.jpg',
        'Цвикельбир, в сущности — игристая разновидность баварского Келлербира (от немецкого Keller — погреб, погребное пиво). Это пиво содержит немного меньше спирта и хмеля. Доля алкоголя обычно ниже 5 %. Название Цвикельбир произошло от немецкого слова «цвикель» (Zwickel), что означает «кран для отбора проб», помещаемый на краю бочки или другой ёмкости, чтобы снимать пробы с напитка и узнавать, как протекает процесс ферментации.');
INSERT INTO public.book (uuid, sort, type, image, content)
VALUES ('d2bab2f3-0b3c-486f-b2b6-37b9c2d720cb', 'Лагер', 'Венский лагер',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Cerveza_negra_modelo_glass.jpg/548px-Cerveza_negra_modelo_glass.jpg',
        e 'Своими характеристиками этот вид пива обязан венскому солоду. Используется только высококачественный солод в сочетании с континентальным европейским хмелем благородных сортов. Американские версии являются более крепкими, сухими и горькими, в то время как европейские, как правило, сладкие.

Этот тип пива отличается ярко-красным цветом от янтарного до медного; прозрачностью; обильной желтой и устойчивой пеной, богатым солодовым вкусом и ароматом венского или мюнхенского солода с хмелевыми нотками.

Многие мексиканские янтарные и тёмные лагеры в прошлом были аутентичны, но сегодня больше всего напоминают сладкий, с большим количеством добавок тёмный американский лагер.');
INSERT INTO public.book (uuid, sort, type, image, content)
VALUES ('b09ac507-fa25-4a59-a837-fe6feb40542a', 'Эль', 'Стаут',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Stout.jpg/548px-Stout.jpg', e 'Ста́ут (англ. stout) — тёмный элевый (верхового брожения) сорт пива, приготовленный с использованием жжёного солода, получаемого путём прожарки ячменного зерна, с добавлением карамельного солода.

Первое известное использование слова «стаут» для пива — в документе от 1677 года, найденном в Эгертонских рукописях, — относилось к его крепости. Название «портер» впервые было использовано в 1721 году для описания тёмно-коричневого пива. Из-за огромной популярности портеров пивовары производили их с разной крепостью. Более крепкие сорта пива, обычно 7 % или 8 % алкоголя по объёму, назывались «стаут-портер», поэтому история и развитие стаута и портера переплелись, а термин «стаут» стал прочно ассоциироваться с тёмным, а не просто крепким пивом');
INSERT INTO public.book (uuid, sort, type, image, content)
VALUES ('df926e00-5e1f-4ca0-90bc-3908765664f7', 'Смешанное', 'Ламбик',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/3/30/Bieren_uit_de_streek_rond_brussel.jpg/548px-Bieren_uit_de_streek_rond_brussel.jpg',
        e 'Ламби́к — это обобщенное название семейства сортов пива, которое варят в регионе Пайоттенланд в Бельгии к юго-западу от Брюсселя и в самом Брюсселе с XIII века. Популярными сортами ламбика являются гёз  (англ.)рус., крик и фрамбуаз.

Ламбик отличается от большинства других сортов пива тем, что его ферментация происходит под воздействием диких дрожжей и бактерий, обитающих в долине Сенны, в отличие от воздействия тщательно культивируемых штаммов пивных дрожжей. Этот процесс придает пиву характерный вкус: сухой, виноградный, сидровый, часто с терпким послевкусием');
INSERT INTO public.book (uuid, sort, type, image, content)
VALUES ('b75488bd-8889-43cc-849b-e5ac45fd2219', 'Смешанное', 'Бьер-де-Гард',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Jielbeaumadier_bieres_du_nord_2008.jpg/548px-Jielbeaumadier_bieres_du_nord_2008.jpg',
        e 'Бьер-де-Гард (фр. Bière de Garde, букв. «пиво для хранения») — традиционный региональный сорт французского пива, производящийся пивоварнями региона Нор-Па-де-Кале на севере Франции. Учитывая географическую близость к Бельгии, сорт подобен бельгийскому сезонному, однако отличается сладким солодовым вкусом.

Отличительной особенностью сорта является достаточно высокое содержание алкоголя, который, как видно из его названия, был призван обеспечить более долгий срок хранения напитка. Традиционной тарой для Бьер-де-Гард являются стеклянные бутылки, закрытые пробкой, подобные бутылкам с игристыми винами.');
INSERT INTO public.book (uuid, sort, type, image, content)
VALUES ('605b148b-cb37-405c-99e0-bfd91a2a23c8', 'Смешанное', 'Барливайн',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/Ægir_Tors_Hammer_Barleywine.jpg/548px-Ægir_Tors_Hammer_Barleywine.jpg',
        'Барлива́йн (англ. Barley wine, букв. ячменное вино) — крепкое английское пиво, разновидность крепкого эля (Strong Ale) от коричнево-золотистого до чёрного цвета с содержанием алкоголя 8,0-12,0 %. По мнению некоторых авторов, обособлять барливайн в отдельный стиль излишне, и это лишь изменение стиля старый эль. Основные производители такого эля — Англия и США, но барливайн производят также и отдельные пивоваренные заводы в Израиле, Канаде, Австралии, Бельгии, Дании, Италии и других странах.');
INSERT INTO public.book (uuid, sort, type, image, content)
VALUES ('04600639-1ada-45e4-b929-2285f5abdc4b', 'Смешанное', 'Раухбир',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Bierschlenkerla.jpg/440px-Bierschlenkerla.jpg', e 'Ра́ухбир (нем. Rauchbier — «копчёное пиво») — пиво из Франконии с особым вкусом, который придает ему подкопчённый на огне солод. Характерный дымный привкус напоминает копченое мясо, которое идеально сочетается с этим видом пива.

Существует легенда о появлении раухбира, по которой однажды в пивоварне произошёл пожар и хранившийся там солод пропитался дымом. Пивовар, будучи бедным, был вынужден продавать пиво из этого испорченного солода. Его вкус, однако, очень понравился посетителям. Так возник новый сорт пива.

Солод для производства раухбира должен быть высушен. Так как сушка на солнце была возможна не во всех регионах, применялась сушка на открытом огне, как и в случае китайского копчёного чая. Тепло и дым проходили сквозь лежащий на решетке сырой солод и высушивали его, тем самым увеличивая срок хранения. С началом индустриализации возникли новые способы сушки с использованием угля и нефти. Эти технологии были дешевле и постепенно вытеснили старый способ сушки на костре.');

create table if not exists role (
    id   serial  not null,
    name varchar not null,

    constraint role_id_pk primary key (id)
);

insert into role values (1, 'admin');
insert into role values (2, 'base');

create table if not exists account
(
    uuid     uuid    not null default uuid_generate_v4(),
    username varchar not null,
    name     varchar not null,
    lastname varchar not null,
    birthday date    not null,
    email    varchar not null,
    password varchar not null,
    avatar   varchar not null default 'https://mirtex.ru/wp-content/uploads/2023/04/unnamed.jpg',
    about    varchar not null default '-',
    role_id  varchar not null default 2


    constraint account_uuid_pk primary key (uuid),
    constraint account_username_uk unique (username),
    constraint account_email_uk unique (email),
    constraint account_role_fk foreign key (role_id) references role (id)
);

create table if not exists post
(
    uuid                uuid    not null default uuid_generate_v4(),
    author_uuid         uuid    not null,
    title               varchar not null,
    content             varchar not null,
    image               varchar not null,
    date_of_publication date    not null,


    constraint post_uuid_pk primary key (uuid),
    constraint post_author_fk foreign key (author_uuid) references account (uuid)
);

create table if not exists comment
(
    uuid                uuid    not null default uuid_generate_v4(),
    author_uuid         uuid    not null,
    post_uuid           uuid    not null,
    content             varchar not null,
    date_of_publication date    not null,

    constraint comment_uuid_pk primary key (uuid),
    constraint comment_author_uuid foreign key (author_uuid) references account (uuid),
    constraint comment_post_uuid foreign key (post_uuid) references post (uuid)
)