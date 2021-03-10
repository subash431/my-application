package com.example.consultancy.helper;

import com.example.consultancy.model.Consultancy;

import java.util.ArrayList;

public class ConsultancyList {

    public static ArrayList<Consultancy> produce(){
        ArrayList<Consultancy> consultancyArrayList = new ArrayList<>();

        Consultancy consultancy = new Consultancy();
        consultancy.setName("Kings Institute");
        consultancy.setAddress("Mahendrapool");
        consultancy.setPhoneNumber("061540404");
        consultancy.setTitleDescription("WE’LL FIND YOU THE RIGHT OPTION");
        consultancy.setDescription("King's Institute has been assisting degree granting colleges and universities, language schools, public/private schools in their marketing and recruitment efforts internationally by complementing the efforts of their international recruitment office. We provide the school with completed student application packets. Many times, because of cultural differences, Nepalese students are confused about the required documents. We provide counseling on what documents to prepare for a complete application.");

        Consultancy consultancy1 = new Consultancy();
        consultancy1.setName("Top Education Consultancy Pvt. Ltd.");
        consultancy1.setAddress("Chipledhunga");
        consultancy1.setPhoneNumber("061523684");
        consultancy1.setTitleDescription("Pioneer in the field of consultancy from last decade.");
        consultancy1.setDescription("TEC (Top Education Consultancy) has been pioneer in the field of consultancy from last decade. Japan is one of the most developed countries in the world. Especially for a developing country like our’s, Japan has always been a source of inspiration. For the sole purpose of importing knowledge, technology and quality educational system from Japan through the students who study and return from there, for the development of educational system in Nepal, Top Education Consultancy was established in 2002, located in the heart of Pokhara, in Chilpedhunga, Pokhara-4, it has been providing qualitative Japanese language education to enhance students’ further educational career. This institute was established to make Japanese learning easy and to provide quality education, information about Japanese culture, traditions, Japanese schools and universities.");

        Consultancy consultancy2 = new Consultancy();
        consultancy2.setName("Miracle Educational consultancy");
        consultancy2.setAddress("Chipledhunga");
        consultancy2.setPhoneNumber("061535524");
        consultancy2.setTitleDescription("An umbrella organization of educational consultancies of Nepal and NAFSA.");
        consultancy2.setDescription("Welcome To Miracle Educational consultancy\n" +
                "Miracle Institute Pvt. Ltd. is a leading educational consultancy in Pokhara, Nepal. The institute has been duly registered in the Ministry of Education and a certified agency by ICEF. We are the member of ECAN, an umbrella organization of educational consultancies of Nepal and NAFSA. The institute was established in 2009 and since then it has achieved many notable successes of worth mentioning. We provide personal and professional services to our students. We have developed necessary infrastructures to run the consultancy with ease and success. Perhaps, this is the reason why we have been way ahead of other consultancies.");


        consultancyArrayList.add(consultancy);
        consultancyArrayList.add(consultancy1);
        consultancyArrayList.add(consultancy2);

        return consultancyArrayList;
    }
}
