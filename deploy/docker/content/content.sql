DELETE FROM heart_symbol
WHERE id IN (-15, -16);
DELETE FROM pray
WHERE id IN (-17, -18, -19, -20);
DELETE from wave WHERE id=-11;
INSERT INTO public.wave (id, create_time, buried, content, flag, sign, creator, mound_time, mound)
VALUES (-11, to_timestamp('2016-01-01','yyyy-mm-dd') , false, '<span class=\"Apple-tab-span\" style=\"white-space: pre;\">	<span style=\"font-size: large;\">	</span></span><span
        style=\"font-size: large;\">Driving small boat in the calm , borderless sea, wanting to find a place to berth. &nbsp;</span>
<div><span style=\"font-size: large;\"><span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span>Sky is darkening , seems storm comes.While wind becomes stronger and stronger , waves appears from surface of sea.</span>
</div>
<div><span style=\"font-size: large;\"><span class=\"Apple-tab-span\" style=\"white-space: pre;\">	</span>The fat whale jump out and into the sea, make it so unrest...</span>
</div>
<div><span style=\"font-size: large;\">&nbsp; &nbsp; &nbsp;</span><img
        src=\"/resources/STAR-0000/sea.jpg\"></div>', 2, null, NULL , null, null);

DELETE from flower WHERE id=-12;
INSERT INTO public.flower (id, create_time, buried, content, flag, sign, creator, mound_time, mound)
VALUES (-12, to_timestamp('2016-01-01','yyyy-mm-dd') , false, '<span style=\"font-size: large;\"><span class=\"Apple-tab-span\"
                                      style=\"white-space:pre\">	</span>On the way to cyan mountain，meets the powder deer, attracted deeply to her beautiful appearance .</span>
<div><span style=\"font-size: large;\"><span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span>follow her  to a flower forest , suddenly body is sinking ，seems there is swamp beneath flowers.</span>
</div>
<div style=\"font-size: 10pt; text-align: center;\"><img src=\"/resources/STAR-0000/swamp.jpg\"></div>', 2, null, NULL, null, null);

DELETE FROM stone WHERE id=-13;
INSERT INTO public.stone (id, create_time, buried, content, flag, sign, creator)
VALUES (-13, to_timestamp('2016-01-01','yyyy-mm-dd') , false, '<span style=\"font-size: large;\"><span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span>At foot of the cyan mountain , Looking up ，jungle in mountain is dense ，road to peek is complex and bending，The mountain eagle is flying.</span>
<div><span style=\"font-size: large;\"><span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span>Want to climb to the top, take a watch of view from peak.</span>
</div>
<div style=\"font-size: 10pt; text-align: center;\"><img src=\"/resources/STAR-0000/hillock.jpg\"></div>', 2, null, NULL);

DELETE from wish WHERE id=-14;
INSERT INTO public.wish (id, create_time, buried, content, flag, sign, creator)
VALUES (-14, to_timestamp('2016-01-01','yyyy-mm-dd') , false, '<span style=\"font-size: large;\"><span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span>Looking up the sky and stars，stars flashes，silent.</span>
<div><span style=\"font-size: large;\"><span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span>Forgetting about all things ，feel with peace and calm.</span>
</div>
<div style=\"text-align: center;\"><img src=\"/resources/STAR-0000/star.jpeg\"></div>', 2, null, NULL);

INSERT INTO public.heart_symbol (id, age, belong) VALUES (-15, 11, -11);
INSERT INTO public.heart_symbol (id, age, belong) VALUES (-16, 18, -12);

INSERT INTO public.pray (id, create_time, wish) VALUES (-17, NULL , -14);
INSERT INTO public.pray (id, create_time, wish) VALUES (-18, NULL , -14);
INSERT INTO public.pray (id, create_time, wish) VALUES (-19, NULL , -14);
INSERT INTO public.pray (id, create_time, wish) VALUES (-20, NULL , -14);