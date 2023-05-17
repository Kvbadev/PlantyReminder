package com.example.plantyreminder

import com.example.plantyreminder.data.dto.ApiSearchResultObject
import com.example.plantyreminder.data.dto.ApiSearchResultObjectList
import com.example.plantyreminder.utils.ApiSearchResultDeserializer
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GsonConverterUnitTest {

    private val gson = GsonBuilder().apply {
        registerTypeAdapter(ApiSearchResultObject::class.java, ApiSearchResultDeserializer())
    }.create()

    @Test
    fun convertCorrectApiResponse(): Unit = runBlocking {
        val testObject = gson.fromJson(correctMessage, ApiSearchResultObjectList::class.java)
        assertEquals(listOf("Silver Fir","Concolor Fir","Colorado Fir") ,testObject.results[0].otherNames)
        assertEquals(listOf("Abies concolor") ,testObject.results[0].scientificNames)
        assertEquals(3, testObject.results[0].id)
        assertEquals( """https://perenual.com/storage/species_image/3_abies_concolor/og/52292935430_f4f3b22614_b.jpg""",testObject.results[0].imageUrl?.url)
    }

    @Test
    fun convertPaywallApiResponse(): Unit = runBlocking {
        val testObject = gson.fromJson(paywallMessage, ApiSearchResultObjectList::class.java)
        assertEquals(5809, testObject.results[0].id)
        assertEquals("rose cactus", testObject.results[0].commonName)
        assertNull(testObject.results[0].imageUrl)
        assertNull(testObject.results[0].otherNames)
        assertNull(testObject.results[0].scientificNames)
    }


    companion object {
        const val correctMessage = """{"data":[{"id":3,"common_name":"White Fir","scientific_name":["Abies concolor"],"other_name":["Silver Fir","Concolor Fir","Colorado Fir"],"cycle":"Perennial","watering":"Average","sunlight":["Full sun","part shade"],"default_image":{"license":5,"license_name":"Attribution-ShareAlike License","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/2.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/3_abies_concolor\/og\/52292935430_f4f3b22614_b.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/3_abies_concolor\/regular\/52292935430_f4f3b22614_b.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/3_abies_concolor\/medium\/52292935430_f4f3b22614_b.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/3_abies_concolor\/small\/52292935430_f4f3b22614_b.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/3_abies_concolor\/thumbnail\/52292935430_f4f3b22614_b.jpg"}},{"id":4,"common_name":"Candicans White Fir","scientific_name":["Abies concolor 'Candicans'"],"other_name":["Silver Fir","Concolor Fir","Colorado Fir"],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade"],"default_image":{"license":5,"license_name":"Attribution-ShareAlike License","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/2.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/4_abies_concolor_candicans\/og\/49283844888_332c9e46f2_b.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/4_abies_concolor_candicans\/regular\/49283844888_332c9e46f2_b.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/4_abies_concolor_candicans\/medium\/49283844888_332c9e46f2_b.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/4_abies_concolor_candicans\/small\/49283844888_332c9e46f2_b.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/4_abies_concolor_candicans\/thumbnail\/49283844888_332c9e46f2_b.jpg"}},{"id":5,"common_name":"Fraser Fir","scientific_name":["Abies fraseri"],"other_name":["Southern Fir"],"cycle":"Perennial","watering":"Average","sunlight":["Full sun","part shade"],"default_image":{"license":4,"license_name":"Attribution License","license_url":"https:\/\/creativecommons.org\/licenses\/by\/2.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/5_abies_fraseri\/og\/36843539702_e80fc436e0_b.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/5_abies_fraseri\/regular\/36843539702_e80fc436e0_b.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/5_abies_fraseri\/medium\/36843539702_e80fc436e0_b.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/5_abies_fraseri\/small\/36843539702_e80fc436e0_b.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/5_abies_fraseri\/thumbnail\/36843539702_e80fc436e0_b.jpg"}},{"id":7,"common_name":"Alpine Fir","scientific_name":["Abies lasiocarpa"],"other_name":["Subalpine Fir","Rocky Mountain Fir"],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade"],"default_image":{"license":5,"license_name":"Attribution-ShareAlike License","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/2.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/7_abies_lasiocarpa\/og\/51002756843_74fae3c2fa_b.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/7_abies_lasiocarpa\/regular\/51002756843_74fae3c2fa_b.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/7_abies_lasiocarpa\/medium\/51002756843_74fae3c2fa_b.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/7_abies_lasiocarpa\/small\/51002756843_74fae3c2fa_b.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/7_abies_lasiocarpa\/thumbnail\/51002756843_74fae3c2fa_b.jpg"}},{"id":9,"common_name":"Noble Fir","scientific_name":["Abies procera"],"other_name":["Red Fir","White Fir"],"cycle":"Perennial","watering":"Average","sunlight":["Sun"],"default_image":{"license":5,"license_name":"Attribution-ShareAlike License","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/2.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/9_abies_procera\/og\/49107504112_6bd7effb8b_b.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/9_abies_procera\/regular\/49107504112_6bd7effb8b_b.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/9_abies_procera\/medium\/49107504112_6bd7effb8b_b.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/9_abies_procera\/small\/49107504112_6bd7effb8b_b.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/9_abies_procera\/thumbnail\/49107504112_6bd7effb8b_b.jpg"}},{"id":11,"common_name":"Snakebark Maple","scientific_name":["Acer davidii"],"other_name":["Father David's Maple","Pere David's Maple"],"cycle":"Perennial","watering":"Average","sunlight":["Full sun","part shade"],"default_image":{"license":5,"license_name":"Attribution-ShareAlike License","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/2.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/11_acer_davidii\/og\/6868591754_f4ac5b0510_b.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/11_acer_davidii\/regular\/6868591754_f4ac5b0510_b.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/11_acer_davidii\/medium\/6868591754_f4ac5b0510_b.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/11_acer_davidii\/small\/6868591754_f4ac5b0510_b.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/11_acer_davidii\/thumbnail\/6868591754_f4ac5b0510_b.jpg"}},{"id":18,"common_name":"Cutleaf Fullmoon Maple","scientific_name":["Acer japonicum 'Aconitifolium'"],"other_name":["fernleaf full moon maple"],"cycle":"Perennial","watering":"Average","sunlight":["Full sun","part shade"],"default_image":{"license":4,"license_name":"Attribution License","license_url":"https:\/\/creativecommons.org\/licenses\/by\/2.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/18_acer_japonicum_aconitifolium\/og\/23528789198_c419363323_b.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/18_acer_japonicum_aconitifolium\/regular\/23528789198_c419363323_b.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/18_acer_japonicum_aconitifolium\/medium\/23528789198_c419363323_b.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/18_acer_japonicum_aconitifolium\/small\/23528789198_c419363323_b.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/18_acer_japonicum_aconitifolium\/thumbnail\/23528789198_c419363323_b.jpg"}},{"id":19,"common_name":"Attaryi Fullmoon Maple*","scientific_name":["Acer japonicum 'Attaryi'"],"other_name":[],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade"],"default_image":{"license":451,"license_name":"CC0 1.0 Universal (CC0 1.0) Public Domain Dedication","license_url":"https:\/\/creativecommons.org\/publicdomain\/zero\/1.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/19_acer_japonicum_attaryi\/og\/pexels-photo-669323.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/19_acer_japonicum_attaryi\/regular\/pexels-photo-669323.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/19_acer_japonicum_attaryi\/medium\/pexels-photo-669323.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/19_acer_japonicum_attaryi\/small\/pexels-photo-669323.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/19_acer_japonicum_attaryi\/thumbnail\/pexels-photo-669323.jpg"}},{"id":21,"common_name":"Emmett's Pumpkin Fullmoon Maple","scientific_name":["Acer japonicum 'Emmett's Pumpkin'"],"other_name":[],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade"],"default_image":{"license":45,"license_name":"Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0)","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/3.0\/deed.en","original_url":"https:\/\/perenual.com\/storage\/species_image\/21_acer_japonicum_emmetts_pumpkin\/og\/Acer_shirasawanum_28golden_fullmoon_maple29_2_284633285838129.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/21_acer_japonicum_emmetts_pumpkin\/regular\/Acer_shirasawanum_28golden_fullmoon_maple29_2_284633285838129.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/21_acer_japonicum_emmetts_pumpkin\/medium\/Acer_shirasawanum_28golden_fullmoon_maple29_2_284633285838129.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/21_acer_japonicum_emmetts_pumpkin\/small\/Acer_shirasawanum_28golden_fullmoon_maple29_2_284633285838129.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/21_acer_japonicum_emmetts_pumpkin\/thumbnail\/Acer_shirasawanum_28golden_fullmoon_maple29_2_284633285838129.jpg"}},{"id":25,"common_name":"Flamingo Boxelder","scientific_name":["Acer negundo 'Flamingo'"],"other_name":["Manitoba Maple","box elder"],"cycle":"Perennial","watering":"Frequent","sunlight":["Full sun","part shade"],"default_image":{"license":5,"license_name":"Attribution-ShareAlike License","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/2.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/25_acer_negundo_flamingo\/og\/5867345385_a9dff5bee7_b.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/25_acer_negundo_flamingo\/regular\/5867345385_a9dff5bee7_b.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/25_acer_negundo_flamingo\/medium\/5867345385_a9dff5bee7_b.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/25_acer_negundo_flamingo\/small\/5867345385_a9dff5bee7_b.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/25_acer_negundo_flamingo\/thumbnail\/5867345385_a9dff5bee7_b.jpg"}},{"id":26,"common_name":"Kelly's Gold Boxelder","scientific_name":["Acer negundo 'Kelly's Gold'"],"other_name":["Manitoba Maple","box elder"],"cycle":"Perennial","watering":"Frequent","sunlight":["Full sun"],"default_image":{"license":5,"license_name":"Attribution-ShareAlike License","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/2.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/26_acer_negundo_kellys_gold\/og\/28819730054_e2a2b797c9_b.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/26_acer_negundo_kellys_gold\/regular\/28819730054_e2a2b797c9_b.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/26_acer_negundo_kellys_gold\/medium\/28819730054_e2a2b797c9_b.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/26_acer_negundo_kellys_gold\/small\/28819730054_e2a2b797c9_b.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/26_acer_negundo_kellys_gold\/thumbnail\/28819730054_e2a2b797c9_b.jpg"}},{"id":27,"common_name":"Japanese Maple","scientific_name":["Acer palmatum"],"other_name":[],"cycle":"Perennial","watering":"Average","sunlight":["Full sun","part shade"],"default_image":{"license":45,"license_name":"Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0)","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/3.0\/deed.en","original_url":"https:\/\/perenual.com\/storage\/species_image\/27_acer_palmatum\/og\/2560px-Acer_palmatum_27Bloodgood27_kz01.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/27_acer_palmatum\/regular\/2560px-Acer_palmatum_27Bloodgood27_kz01.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/27_acer_palmatum\/medium\/2560px-Acer_palmatum_27Bloodgood27_kz01.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/27_acer_palmatum\/small\/2560px-Acer_palmatum_27Bloodgood27_kz01.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/27_acer_palmatum\/thumbnail\/2560px-Acer_palmatum_27Bloodgood27_kz01.jpg"}},{"id":28,"common_name":"Aka Shigitatsu Sawa Japanese Maple","scientific_name":["Acer palmatum 'Aka Shigitatsu Sawa'"],"other_name":["Samurai"],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade"],"default_image":{"license":45,"license_name":"Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0)","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/3.0\/deed.en","original_url":"https:\/\/perenual.com\/storage\/species_image\/28_acer_palmatum_aka_shigitatsu_sawa\/og\/Acer_palmatum_Aka_shigitatsu_sawa_2zz.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/28_acer_palmatum_aka_shigitatsu_sawa\/regular\/Acer_palmatum_Aka_shigitatsu_sawa_2zz.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/28_acer_palmatum_aka_shigitatsu_sawa\/medium\/Acer_palmatum_Aka_shigitatsu_sawa_2zz.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/28_acer_palmatum_aka_shigitatsu_sawa\/small\/Acer_palmatum_Aka_shigitatsu_sawa_2zz.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/28_acer_palmatum_aka_shigitatsu_sawa\/thumbnail\/Acer_palmatum_Aka_shigitatsu_sawa_2zz.jpg"}},{"id":29,"common_name":"Alpenweiss Variegated Dwarf Japanese Maple*","scientific_name":["Acer palmatum 'Alpenweiss'"],"other_name":[],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade"],"default_image":{"license":5,"license_name":"Attribution-ShareAlike License","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/2.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/og\/49255769768_df55596553_b.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/regular\/49255769768_df55596553_b.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/medium\/49255769768_df55596553_b.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/small\/49255769768_df55596553_b.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/thumbnail\/49255769768_df55596553_b.jpg"}},{"id":30,"common_name":"Ao Shime No Uchi Japanese Maple","scientific_name":["Acer palmatum 'Ao Shime No Uchi'"],"other_name":[],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade"],"default_image":{"license":45,"license_name":"Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0)","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/3.0\/deed.en","original_url":"https:\/\/perenual.com\/storage\/species_image\/30_acer_palmatum_ao_shime_no_uchi\/og\/Acer_palmatum_Ao_shime_no_uchi_3zz.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/30_acer_palmatum_ao_shime_no_uchi\/regular\/Acer_palmatum_Ao_shime_no_uchi_3zz.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/30_acer_palmatum_ao_shime_no_uchi\/medium\/Acer_palmatum_Ao_shime_no_uchi_3zz.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/30_acer_palmatum_ao_shime_no_uchi\/small\/Acer_palmatum_Ao_shime_no_uchi_3zz.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/30_acer_palmatum_ao_shime_no_uchi\/thumbnail\/Acer_palmatum_Ao_shime_no_uchi_3zz.jpg"}},{"id":31,"common_name":"Aoyagi Japanese Maple*","scientific_name":["Acer palmatum 'Aoyagi'"],"other_name":["Green Coral Japanese Maple"],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade"],"default_image":{"license":451,"license_name":"CC0 1.0 Universal (CC0 1.0) Public Domain Dedication","license_url":"https:\/\/creativecommons.org\/publicdomain\/zero\/1.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/164_acer_shirasawanum_yasemin\/og\/acer_palmatum_japanese_maples_trees_red_red_leaves_blue_sky_blue_white-1126158.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/164_acer_shirasawanum_yasemin\/regular\/acer_palmatum_japanese_maples_trees_red_red_leaves_blue_sky_blue_white-1126158.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/164_acer_shirasawanum_yasemin\/medium\/acer_palmatum_japanese_maples_trees_red_red_leaves_blue_sky_blue_white-1126158.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/164_acer_shirasawanum_yasemin\/small\/acer_palmatum_japanese_maples_trees_red_red_leaves_blue_sky_blue_white-1126158.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/164_acer_shirasawanum_yasemin\/thumbnail\/acer_palmatum_japanese_maples_trees_red_red_leaves_blue_sky_blue_white-1126158.jpg"}},{"id":32,"common_name":"Arakawa Cork Bark Japanese Maple","scientific_name":["Acer palmatum 'Arakawa'"],"other_name":["Rough-Bark Japanese Maple","Arakawa Ukon"],"cycle":"Perennial","watering":"Average","sunlight":["Full sun","part shade"],"default_image":{"license":5,"license_name":"Attribution-ShareAlike License","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/2.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/og\/49255769768_df55596553_b.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/regular\/49255769768_df55596553_b.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/medium\/49255769768_df55596553_b.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/small\/49255769768_df55596553_b.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/thumbnail\/49255769768_df55596553_b.jpg"}},{"id":33,"common_name":"Asahi Zuru Japanese Maple","scientific_name":["Acer palmatum 'Asahi Zuru'"],"other_name":["Rising Sun","Dawn Swan"],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade"],"default_image":{"license":5,"license_name":"Attribution-ShareAlike License","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/2.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/og\/49255769768_df55596553_b.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/regular\/49255769768_df55596553_b.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/medium\/49255769768_df55596553_b.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/small\/49255769768_df55596553_b.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/thumbnail\/49255769768_df55596553_b.jpg"}},{"id":34,"common_name":"Ribbon-leaf Japanese Maple*","scientific_name":["Acer palmatum 'Atrolineare'"],"other_name":["Red Strap-Leaf Japanese Maple"],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade"],"default_image":{"license":45,"license_name":"Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0)","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/3.0\/deed.en","original_url":"https:\/\/perenual.com\/storage\/species_image\/34_acer_palmatum_atrolineare\/og\/2560px-Acer_Palmatum_27Red_Pygmy27.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/34_acer_palmatum_atrolineare\/regular\/2560px-Acer_Palmatum_27Red_Pygmy27.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/34_acer_palmatum_atrolineare\/medium\/2560px-Acer_Palmatum_27Red_Pygmy27.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/34_acer_palmatum_atrolineare\/small\/2560px-Acer_Palmatum_27Red_Pygmy27.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/34_acer_palmatum_atrolineare\/thumbnail\/2560px-Acer_Palmatum_27Red_Pygmy27.jpg"}},{"id":35,"common_name":"Purple-Leaf Japanese Maple","scientific_name":["Acer palmatum 'Atropurpureum'"],"other_name":[],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade","full shade"],"default_image":{"license":4,"license_name":"Attribution License","license_url":"https:\/\/creativecommons.org\/licenses\/by\/2.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/35_acer_palmatum_atropurpureum\/og\/5656874577_617188e307_b.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/35_acer_palmatum_atropurpureum\/regular\/5656874577_617188e307_b.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/35_acer_palmatum_atropurpureum\/medium\/5656874577_617188e307_b.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/35_acer_palmatum_atropurpureum\/small\/5656874577_617188e307_b.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/35_acer_palmatum_atropurpureum\/thumbnail\/5656874577_617188e307_b.jpg"}},{"id":36,"common_name":"Aureum Japanese Maple*","scientific_name":["Acer palmatum 'Aureum'"],"other_name":[],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade"],"default_image":{"license":45,"license_name":"Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0)","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/3.0\/deed.en","original_url":"https:\/\/perenual.com\/storage\/species_image\/36_acer_palmatum_aureum\/og\/2560px-Acer_shirasawanum_22aureum22.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/36_acer_palmatum_aureum\/regular\/2560px-Acer_shirasawanum_22aureum22.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/36_acer_palmatum_aureum\/medium\/2560px-Acer_shirasawanum_22aureum22.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/36_acer_palmatum_aureum\/small\/2560px-Acer_shirasawanum_22aureum22.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/36_acer_palmatum_aureum\/thumbnail\/2560px-Acer_shirasawanum_22aureum22.jpg"}},{"id":37,"common_name":"Autumn Fire Japanese Maple","scientific_name":["Acer palmatum 'Autumn Fire'"],"other_name":["Herbstfeuer"],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade"],"default_image":{"license":45,"license_name":"Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0)","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/3.0\/deed.en","original_url":"https:\/\/perenual.com\/storage\/species_image\/37_acer_palmatum_autumn_fire\/og\/2560px-Acer_palmatum_fall_foliage_01.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/37_acer_palmatum_autumn_fire\/regular\/2560px-Acer_palmatum_fall_foliage_01.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/37_acer_palmatum_autumn_fire\/medium\/2560px-Acer_palmatum_fall_foliage_01.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/37_acer_palmatum_autumn_fire\/small\/2560px-Acer_palmatum_fall_foliage_01.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/37_acer_palmatum_autumn_fire\/thumbnail\/2560px-Acer_palmatum_fall_foliage_01.jpg"}},{"id":38,"common_name":"Azuma Murasaki Japanese Maple","scientific_name":["Acer palmatum 'Azuma Murasaki'"],"other_name":[],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade"],"default_image":{"license":5,"license_name":"Attribution-ShareAlike License","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/2.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/og\/49255769768_df55596553_b.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/regular\/49255769768_df55596553_b.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/medium\/49255769768_df55596553_b.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/small\/49255769768_df55596553_b.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/thumbnail\/49255769768_df55596553_b.jpg"}},{"id":39,"common_name":"Beni Kawa Coral Bark Japanese Maple*","scientific_name":["Acer palmatum 'Beni Kawa'"],"other_name":[],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade"],"default_image":{"license":4,"license_name":"Attribution License","license_url":"https:\/\/creativecommons.org\/licenses\/by\/2.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/39_acer_palmatum_beni_kawa\/og\/5695802528_935d72efaa_b.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/39_acer_palmatum_beni_kawa\/regular\/5695802528_935d72efaa_b.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/39_acer_palmatum_beni_kawa\/medium\/5695802528_935d72efaa_b.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/39_acer_palmatum_beni_kawa\/small\/5695802528_935d72efaa_b.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/39_acer_palmatum_beni_kawa\/thumbnail\/5695802528_935d72efaa_b.jpg"}},{"id":40,"common_name":"Beni Otake Japanese Maple","scientific_name":["Acer palmatum 'Beni Otake'"],"other_name":["Big Red Bamboo"],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade"],"default_image":{"license":45,"license_name":"Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0)","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/3.0\/deed.en","original_url":"https:\/\/perenual.com\/storage\/species_image\/40_acer_palmatum_beni_otake\/og\/Acer_palmatum_27Oshio_beni27_-_JPG2.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/40_acer_palmatum_beni_otake\/regular\/Acer_palmatum_27Oshio_beni27_-_JPG2.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/40_acer_palmatum_beni_otake\/medium\/Acer_palmatum_27Oshio_beni27_-_JPG2.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/40_acer_palmatum_beni_otake\/small\/Acer_palmatum_27Oshio_beni27_-_JPG2.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/40_acer_palmatum_beni_otake\/thumbnail\/Acer_palmatum_27Oshio_beni27_-_JPG2.jpg"}},{"id":41,"common_name":"Beni Schichihenge Japanese Maple","scientific_name":["Acer palmatum 'Beni Schichihenge'"],"other_name":[],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade"],"default_image":{"license":5,"license_name":"Attribution-ShareAlike License","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/2.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/og\/49255769768_df55596553_b.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/regular\/49255769768_df55596553_b.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/medium\/49255769768_df55596553_b.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/small\/49255769768_df55596553_b.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/2_abies_alba_pyramidalis\/thumbnail\/49255769768_df55596553_b.jpg"}},{"id":42,"common_name":"Beni Tsukasa Japanese Maple*","scientific_name":["Acer palmatum 'Beni Tsukasa'"],"other_name":["Calico Cloth"],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade"],"default_image":{"license":451,"license_name":"CC0 1.0 Universal (CC0 1.0) Public Domain Dedication","license_url":"https:\/\/creativecommons.org\/publicdomain\/zero\/1.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/42_acer_palmatum_beni_tsukasa\/og\/pexels-photo-11870811.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/42_acer_palmatum_beni_tsukasa\/regular\/pexels-photo-11870811.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/42_acer_palmatum_beni_tsukasa\/medium\/pexels-photo-11870811.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/42_acer_palmatum_beni_tsukasa\/small\/pexels-photo-11870811.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/42_acer_palmatum_beni_tsukasa\/thumbnail\/pexels-photo-11870811.jpg"}},{"id":43,"common_name":"Bloodgood Japanese Maple","scientific_name":["Acer palmatum 'Bloodgood'"],"other_name":[],"cycle":"Perennial","watering":"Average","sunlight":["Full sun","part shade"],"default_image":{"license":5,"license_name":"Attribution-ShareAlike License","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/2.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/43_acer_palmatum_bloodgood\/og\/47923603032_5a96106ac6_b.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/43_acer_palmatum_bloodgood\/regular\/47923603032_5a96106ac6_b.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/43_acer_palmatum_bloodgood\/medium\/47923603032_5a96106ac6_b.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/43_acer_palmatum_bloodgood\/small\/47923603032_5a96106ac6_b.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/43_acer_palmatum_bloodgood\/thumbnail\/47923603032_5a96106ac6_b.jpg"}},{"id":44,"common_name":"Bonfire Japanese Maple","scientific_name":["Acer palmatum 'Bonfire'"],"other_name":["Seigai"],"cycle":"Perennial","watering":"Frequent","sunlight":["full sun","part shade"],"default_image":{"license":451,"license_name":"CC0 1.0 Universal (CC0 1.0) Public Domain Dedication","license_url":"https:\/\/creativecommons.org\/publicdomain\/zero\/1.0\/","original_url":"https:\/\/perenual.com\/storage\/species_image\/44_acer_palmatum_bonfire\/og\/sk3776-image-kwvuoab1.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/44_acer_palmatum_bonfire\/regular\/sk3776-image-kwvuoab1.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/44_acer_palmatum_bonfire\/medium\/sk3776-image-kwvuoab1.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/44_acer_palmatum_bonfire\/small\/sk3776-image-kwvuoab1.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/44_acer_palmatum_bonfire\/thumbnail\/sk3776-image-kwvuoab1.jpg"}},{"id":45,"common_name":"Brandt's Dwarf Japanese Maple","scientific_name":["Acer palmatum 'Brandt's Dwarf'"],"other_name":[],"cycle":"Perennial","watering":"Minimal","sunlight":["full sun","part shade"],"default_image":{"license":45,"license_name":"Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0)","license_url":"https:\/\/creativecommons.org\/licenses\/by-sa\/3.0\/deed.en","original_url":"https:\/\/perenual.com\/storage\/species_image\/45_acer_palmatum_brandts_dwarf\/og\/Acer_palmatum_BotGartenMuenster_Faecherahorn_6691.jpg","regular_url":"https:\/\/perenual.com\/storage\/species_image\/45_acer_palmatum_brandts_dwarf\/regular\/Acer_palmatum_BotGartenMuenster_Faecherahorn_6691.jpg","medium_url":"https:\/\/perenual.com\/storage\/species_image\/45_acer_palmatum_brandts_dwarf\/medium\/Acer_palmatum_BotGartenMuenster_Faecherahorn_6691.jpg","small_url":"https:\/\/perenual.com\/storage\/species_image\/45_acer_palmatum_brandts_dwarf\/small\/Acer_palmatum_BotGartenMuenster_Faecherahorn_6691.jpg","thumbnail":"https:\/\/perenual.com\/storage\/species_image\/45_acer_palmatum_brandts_dwarf\/thumbnail\/Acer_palmatum_BotGartenMuenster_Faecherahorn_6691.jpg"}}],"to":30,"per_page":30,"current_page":1,"from":1,"last_page":257,"total":7710}"""
        const val paywallMessage = """{"data":[{"id":5809,"common_name":"rose cactus","scientific_name":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","other_name":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","cycle":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","watering":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","sunlight":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","default_image":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry"},{"id":7265,"common_name":"Christmas cactus","scientific_name":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","other_name":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","cycle":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","watering":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","sunlight":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","default_image":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry"},{"id":7266,"common_name":"Christmas cactus","scientific_name":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","other_name":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","cycle":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","watering":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","sunlight":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","default_image":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry"},{"id":8551,"common_name":"golden barrel cactus","scientific_name":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","other_name":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","cycle":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","watering":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","sunlight":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","default_image":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry"},{"id":8601,"common_name":"Easter cactus","scientific_name":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","other_name":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","cycle":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","watering":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","sunlight":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry","default_image":"Upgrade Plan For Access https:\/\/perenual.com\/subscription-api-pricing. I'm sorry"}],"to":5,"per_page":30,"current_page":1,"from":1,"last_page":1,"total":5}"""
    }
}

