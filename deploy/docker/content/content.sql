DELETE FROM heart_symbol
WHERE id IN (-5, -6);
DELETE FROM pray
WHERE id IN (-7, -8, -9, -10);
DELETE from wave WHERE id=-1;
INSERT INTO public.wave (id, create_time, buried, content, flag, sign, creator, mound_time, mound)
VALUES (-1, to_timestamp('2016-01-01','yyyy-mm-dd') , false, '<span class=\"Apple-tab-span\" style=\"white-space: pre;\">	<span style=\"font-size: large;\">	</span></span><span
        style=\"font-size: large;\">风平浪静，架着小船行驶在无际的海上，欲寻找停泊的地方休憩。&nbsp;</span>
<div><span style=\"font-size: large;\"><span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span>天色渐渐阴沉，原来有了乌云，似有暴雨来临。伴着越来越强的狂风，海面荡起起伏的浪。</span>
</div>
<div><span style=\"font-size: large;\"><span class=\"Apple-tab-span\" style=\"white-space: pre;\">	</span>肥鲸在水中上下跃入跃出，搅动海面。</span>
</div>
<div><span style=\"font-size: large;\">&nbsp; &nbsp; &nbsp;</span><img
        src=\"/resources/STAR-0000/sea.jpg\"></div>', 1, null, NULL , null, null);

DELETE from flower WHERE id=-2;
INSERT INTO public.flower (id, create_time, buried, content, flag, sign, creator, mound_time, mound)
VALUES (-2, to_timestamp('2016-01-01','yyyy-mm-dd') , false, '<span style=\"font-size: large;\"><span class=\"Apple-tab-span\"
                                      style=\"white-space:pre\">	</span>欲往青山的路上，遇见黛鹿，为其美容吸引。</span>
<div><span style=\"font-size: large;\"><span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span>追随其到一花林，忽觉身体正在陷入，原来花下是沼泽之地。</span>
</div>
<div style=\"font-size: 10pt; text-align: center;\"><img src=\"/resources/STAR-0000/swamp.jpg\"></div>', 1, null, NULL, null, null);

DELETE FROM stone WHERE id=-3;
INSERT INTO public.stone (id, create_time, buried, content, flag, sign, creator)
VALUES (-3, to_timestamp('2016-01-01','yyyy-mm-dd') , false, '<span style=\"font-size: large;\"><span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span>青山脚下抬头望去，山岗上丛林繁密，道路错综弯延，峰顶上山鹰腾翔。</span>
<div><span style=\"font-size: large;\"><span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span>欲攀登到山顶，一览风景，徒步从这里开始。</span>
</div>
<div style=\"font-size: 10pt; text-align: center;\"><img src=\"/resources/STAR-000/hillock.jpg\"></div>', 1, null, NULL);

DELETE from wish WHERE id=-4;
INSERT INTO public.wish (id, create_time, buried, content, flag, sign, creator)
VALUES (-4, to_timestamp('2016-01-01','yyyy-mm-dd') , false, '<span style=\"font-size: large;\"><span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span>仰望星空，星星闪烁，静谧。</span>
<div><span style=\"font-size: large;\"><span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span>这时忘记了其他事情，感受宁静与平和。</span>
</div>
<div style=\"text-align: center;\"><img src=\"/resources/STAR-0000/star.jpeg\"></div>', 1, null, NULL);

INSERT INTO public.heart_symbol (id, age, belong) VALUES (-5, 11, -1);
INSERT INTO public.heart_symbol (id, age, belong) VALUES (-6, 18, -2);

INSERT INTO public.pray (id, create_time, wish) VALUES (-7, NULL , -4);
INSERT INTO public.pray (id, create_time, wish) VALUES (-8, NULL , -4);
INSERT INTO public.pray (id, create_time, wish) VALUES (-9, NULL , -4);
INSERT INTO public.pray (id, create_time, wish) VALUES (-10, NULL , -4);