package com.team5.qu;
import java.util.*;
import java.io.*;

//Scanner, Random, ArrayList, and Collections are used from java.util
public class Main{
    public static void main(String args[]) throws IOException {
        AccountComparator c= new AccountComparator();
        ArrayList<Account> accounts=new ArrayList<>();


            // name, username,  password,  email, phoneNumber,  major, year
            //username must be unique


        //random accounts    
        //String [] majors= {"Accounting (BS)","Anthropology (BA)","Applied Computer Science (BS): Bioinformatics","Applied Computer Science (BS): Computer Game Design","Applied Computer Science (BS): Geography","Applied Computer Science (BS): Software Engineering","Art History (BA)","Art and Visual Technology (BA): All Other Concentrations","Art and Visual Technology (BA): Graphic Design","Art and Visual Technology (BFA): All Other Concentrations","Art and Visual Technology (BFA):Graphic Design","Astronomy (BS)","Atmospheric Sciences (BS)","Bioengineering (BS): Healthcare Informatics","Bioengineering (BS): Neurotechnology and Computational Neuroscience","Bioengineering (BS): Biomedical Imaging & Devices","Bioengineering (BS): Prehealth","Bioengineering (BS): Computational Biomedical Engineering","Bioengineering (BS): Biomaterials and Nanomedicine","Biology (BA)","Biology (BS): Bioinformatics","Biology (BS): Biopsychology","Biology (BS): Biotechnology and Molecular Biology","Biology (BS): Environmental and Conservation Biology","Biology (BS): Microbiology","Biology (BS)","Chemistry (BA): Biochemistry","Chemistry (BA)","Chemistry (BS): Analytical Chemistry","Chemistry (BS): Biochemistry","Chemistry (BS): Environmental Chemistry","Chemistry (BS)","Civil and Infrastructure Engineering (BS)","Communications (BA)","Community Health (BS): Clinical Sciences (Pre Pharmacy)","Community Health (BS): Clinical Sciences (Pre Med)","Community Health (BS): Clinical Sciences (Pre Dental or Optometry","Community Health (BS): Clinical Sciences (Pre Physical Therapy)","Community Health (BS): Clinical Sciences (Pre Physician Assistant)","Community Health (BS): Global Health","Community Health (BS): Clinical Sciences (Pre-Occupational Therapy)","Community Health (BS)","Computational and Data Sciences (BS)","Computer Engineering (BS): Computer Networks","Computer Engineering (BS): Robotics","Computer Engineering (BS): Embedded Systems","Computer Engineering (BS): Hardware and Security Systems","Computer Engineering (BS): Internet of Things","Computer Game Design (BFA)","Computer Science (BS)","Conflict Analysis and Resolution (BA)","Conflict Analysis and Resolution (BS)","Creative Writing (BFA)","Criminology, Law and Society (BA)","Criminology, Law and Society (BS)","Cyber Security Engineering (BS)","Dance (BA)","Dance (BFA)","Early Childhood Education for Diverse Learners, without Concentration (BSEd)","Early Childhood Education for Diverse Learners (BSEd): Early Childhood Special Education Licensure","Early Childhood Education for Diverse Learners (BSEd): Early/Primary Education PreK-3 Licensure","Early Childhood Education for Diverse Learners (BSEd): Dual Licensure","Earth Science (BS): Earth Surface Processes","Earth Science (BS): Environmental Geoscience","Earth Science (BS): Geology","Earth Science (BS): Oceanography and Estuarine Science","Earth Science (BS): Paleontology","Economics (BA): Philosophy, Politics, and Economics","Economics (BA)","Economics (BS): Managerial Economics","Economics (BS): Philosophy, Politics, and Economics","Economics (BS)","Electrical Engineering (BS): Communications and Signal Processing","Electrical Engineering (BS): Computer Engineering","Electrical Engineering (BS): Control Systems","Electrical Engineering (BS): Electronics","Elementary Education, PK-6 Licensure (Bsed)","English (BA)","Environmental Science (BS): Conservation","Environmental Science (BS): Ecological Science","Environmental Science (BS): Environmental Health","Environmental Science (BS): Human and Ecosystem Response to Climate Change","Environmental Science (BS): Marine, Estuarine, and Freshwater Ecology","Environmental Science (BS): Wildlife","Environmental and Sustainability Studies (BA)","Film and Video Studies (BA): Directing","Film and Video Studies (BA): Screenwriting","Film and Video Studies (BA): Production and Post Production","Film and Video Studies (BA): Production","Finance (BS)","Foreign Languages (BA): Arabic","Foreign Languages (BA): Chinese","Foreign Languages (BA): French","Foreign Languages (BA): Spanish","Foreign Languages (BA): Korean","Forensic Science (BS)","Geography (BA): No Concentration","Geography (BA): Environmental Geography","Geography (BA): Geoanthropology","Geography (BA): Health Geography","Geography (BS)","Geology (BA)","Global Affairs (BA)","Government and International Politics (BA): Philosophy, Politics, and Economics","Government and International Politics (BA)","Health Administration (BS): Assisted Living/Senior Housing Administration","Health Administration (BS): Health Informatics","Health Administration (BS): Health Systems Management","Health, Fitness, and Recreation Resources (BS): Parks and Outdoor Recreation","Health, Fitness, and Recreation Resources (BS): Sport Management","Health, Fitness, and Recreation Resources (BS): Therapeutic Recreation","History (BA): Digital History","History (BA): Global History","History (BA): Individualized","History (BA): No Concentration","History (BA): Public History","History (BA): U.S. History","Human Development and Family Science (BA): Adolescent Development and Services","Human Development and Family Science (BA): Adult Development and Agings","Human Development and Family Science (BA): Child Development, Education, and Services","Human Development and Family Science (BA): Family Health and Well Being","Human Development and Family Science (BA): Family Research, Policy, and Advocacy","Individualized Study (BIS)","Information Systems and Operations Management (BS)","Information Technology (BS)","Integrative Studies (BA): Childhood Studies","Integrative Studies (BA or BS): Individualized Concentration","Integrative Studies (BA): International Studies","Integrative Studies (BA): Leadership and Organizational Development","Integrative Studies (BA): Legal Studies","Integrative Studies (BA): Liberal Arts for the Teaching Professions","Integrative Studies (BA): Social innovation and Enterprise","Integrative Studies (BA): Social Justice and Human Rights","Integrative Studies (BA): Social Science for Education","Integrative Studies (BA):Women and Gender Studies Concentration","Integrative Studies (BS): Applied Global Conservation","Integrative Studies (BS): Life Sciences: Pre-Med","Integrative Studies (BS): Life Sciences: Pre-Dental","Integrative Studies (BS): Life Sciences: Pre-Pharmacy","Integrative Studies (BS): Life Sciences: Pre-Occupational Therapy","Integrative Studies (BS): Life Sciences: Pre-Physical Therapy","Integrative Studies (BS): Life Sciences: Pre-Physicians Assistant","Kinesiology (BS)","Management (BS)","Marketing (BS)","Mathematics (BA): Traditional Mathematics","Mathematics (BS): Actuarial Mathematics","Mathematics (BS): Applied Mathematics","Mathematics (BS): Mathematical Statistics","Mathematics (BS): Traditional Mathematics","Mechanical Engineering (BS)","Medical Laboratory Science (BS)","Music (BA): Music Technology","Music (BA)","Music (BM): Choral Education","Music (BM): Composition","Music (BM): Jazz","Music (BM): Keyboard","Music (BM): Music Education","Music (BM): Music Pedagogy","Music (BM): Music Technology - Electroacoustic","Music (BM): Music Technology - Engineering","Music (BM): Music Technology - Recording","Music (BM): Performance","Neuroscience (BS)","Nursing: Traditional Track (BSN)","Philosophy (BA): Philosophy and Law","Philosophy (BA): Philosophy, Politics, and Economics","Philosophy (BA)","Physical Education (BSEd)","Physics (BS): Applied and Engineering Physics Concentration","Physics (BS): Astrophysics Concentration","Physics (BS): Computational Physics Concentration","Physics (BS)","Psychology (BA)","Psychology (BS)","Public Administration (BS)","Rehabilitation Science (BS)","Religious Studies (BA)","Russian and Eurasian Studies (BA): Eurasian Studies","Russian and Eurasian Studies (BA): Russian Language and Cultures","Russian and Eurasian Studies (BA): Russian Studies","Social Work (BSW)","Sociology (BA)","Special Education (Bsed): No Concentration","Special Education (Bsed): General","Special Education (Bsed): Blindness and Visual Impairments","Special Education (Bsed): Adapted K-12 Licensure","Statistics (BS)","Undeclared Engineering","Systems Engineering (BS)","Theater (BA): Education","Theater (BA)","Theater (BFA)","Hospitality, Tourism and Events Management (BS): Events Management","Hospitality, Tourism and Events Management (BS): Hospitality Management","Hospitality, Tourism and Events Management (BS): Tourism Management"};
        ArrayList<String> majors=new ArrayList<>(197);

        ArrayList<String> names=new ArrayList<>(18240);

        ArrayList<String> classeses=new ArrayList<>(100);
        
        int[] years= {1,2,3,4};

        Scanner scnr;
        //File file_w=new File("AccountOutput.txt");
        /*
        //from 0 to bounds
        rand.nextInt(bound);*/
        
        try{
            //Find out how to scan file 
        scnr=new Scanner(new File("Majors.txt"));
        
        while(scnr.hasNextLine()){
            majors.add(scnr.nextLine());
            
        //System.out.println(accounts); 
        }
        
        //System.out.println(accounts); 
        scnr.close();
    }
        catch(FileNotFoundException e){
            System.out.println(e); }
        
            try{
                //Find out how to scan file 
            scnr=new Scanner(new File("name.txt"));
            
            while(scnr.hasNextLine()){
                names.add(scnr.nextLine());
                
            //System.out.println(accounts); 
            }
            
            //System.out.println(accounts); 
            scnr.close();
        }
            catch(FileNotFoundException e){
                System.out.println(e); }
                
                try{
                    //Find out how to scan file 
                scnr=new Scanner(new File("Course.txt"));
                
                while(scnr.hasNextLine()){
                    classeses.add(scnr.nextLine());
                    
                //System.out.println(accounts); 
                }
                
                //System.out.println(accounts); 
                scnr.close();
            }
                catch(FileNotFoundException e){
                    System.out.println(e); }

                //System.out.println(majors);

                
                //System.out.println(names.get(0));


        Random rand=new Random(0);
        for(int i=0;i<100;i++){
            String firstn=names.get(rand.nextInt(names.size()));
            String lastn=names.get(rand.nextInt(names.size()));

            String username=firstn.charAt(0)+lastn+rand.nextInt(10);
            String password="";
            for(int j=0;j<10;j++){
                password+=(char)(rand.nextInt(26)+97);
            }

            String email=username+rand.nextInt(10)+"@masonlive.gmu.edu";
            String phoneNumber="";
            for(int j=0;j<10;j++){
                phoneNumber+=rand.nextInt(10);
            }
            
            String major=majors.get(rand.nextInt(majors.size()));
            
            int year=years[rand.nextInt(years.length)];

            ArrayList<String> cl=new ArrayList<>();
        ArrayList<Preference> preferences=new ArrayList<>();




        ArrayList<String> preferenceOrder=new ArrayList<>(4);
        preferenceOrder.add("gender");
        preferenceOrder.add("available times");
        preferenceOrder.add("location to meet");
        preferenceOrder.add("study techniques");
        Collections.shuffle(preferenceOrder);
        String str="";
        switch(rand.nextInt(3)){
            case 0:
                str="m";
                break;
            case 1:
                str="f";
                break;
            case 2:
                str="a";
                break;
        }
        preferences.add(new Preference(preferenceOrder.indexOf("gender"), "gender", str));
        str="";
        for(int j=0;j<7;j++){
            int randomnum= rand.nextInt(2)+2;
            boolean [] bool=new boolean[4];
            for(int k=0;k<randomnum;k++){
                bool[rand.nextInt(4)]=true;
            }

            for(int k=0;k<bool.length;k++){
                if(bool[k]){
                        switch(k){
                        case 0:
                            str+='1';
                            break;
                        case 1:
                            str+='2';
                            break;
                        case 2:
                            str+='3';  
                            break;          
                        case 3:
                            str+='4';
                            break;
                    }
                }
            }
            if(j<6){
                str+=":";
            }
        }
        preferences.add(new Preference(preferenceOrder.indexOf("available times"), "available times", str));
        str="";
        int randomnum= rand.nextInt(5)+2;
        boolean []bool=new boolean[6];
        for(int j=0;j<randomnum;j++){
            bool[rand.nextInt(6)]=true;
        }
        for(int j=0;j<bool.length;j++){
            if(bool[j]){
                    switch(j){
                    case 0:
                        str+='j';
                        break;
                    case 1:
                        str+='f';
                        break;
                    case 2:
                        str+='m';  
                        break;          
                    case 3:
                        str+='e';
                        break;
                    case 4:
                        str+='d';
                        break;
                    case 5:
                        str+='o';
                        break;
                }
            }
        }
        preferences.add(new Preference(preferenceOrder.indexOf("location to meet"), "location to meet", str));
        str="";
        randomnum= rand.nextInt(4)+1;
        bool=new boolean[4];
        for(int j=0;j<randomnum;j++){
            bool[rand.nextInt(4)]=true;
        }
        for(int j=0;j<bool.length;j++){
            if(bool[j]){
                    switch(j){
                    case 0:
                        str+='f';
                        break;
                    case 1:
                        str+='p';
                        break;
                    case 2:
                        str+='l'; 
                        break;           
                    case 3:
                        str+='o';
                        break;
                }
            }
        }
        preferences.add(new Preference(preferenceOrder.indexOf("study techniques"), "study techniques", str));

            int bounds=rand.nextInt(4)+4;
            for(int j=0;j<bounds;j++){
                String givenClass=classeses.get(rand.nextInt(classeses.size()));
                int x=Collections.binarySearch(cl, givenClass);
                if(x<0){
                    cl.add((x+1)*-1,givenClass);
                }
                else{
                    j--;
                }
            }
            accounts.add(new Account(firstn+" "+lastn, username, password, email, phoneNumber, major, cl,year,preferences));
        }

        
        //accounts.add(new Account(firstn+" "+lastn, username, password, email, phoneNumber, major, year));
        //System.out.println(accounts); 

        


       /* Account a= new Account("name","username","password", "email","phoneNumber", "major", 1);
        //createDummyAccount()
        Account b= new Account("namesas","usernamed","password", "email","phoneNumber", "major", 1);


        /*
        for(int i=0; i< majors.length;i++){
        System.out.println(majors[i]);}*/
/*
        System.out.println(b.getEmail()); 
        System.out.println(b.getMajor());
        System.out.println(b.getName());
        System.out.println(b.getPassword());
        System.out.println(b.getPhoneNumber());
        System.out.println(b.getUsername());
        System.out.println(b.getYear());

        System.out.println(a.getEmail()); 
        System.out.println(a.getMajor());
        System.out.println(a.getName());
        System.out.println(a.getPassword());
        System.out.println(a.getPhoneNumber());
        System.out.println(a.getUsername());
        System.out.println(a.getYear());
*/
/*
System.out.println(c.compare(accounts.get(0),accounts.get(1)));
System.out.println(c.compare(accounts.get(1),accounts.get(1)));
System.out.println(c.compare(accounts.get(1),accounts.get(0)));
*/
/*
        System.out.println(accounts.get(0));
        System.out.println(accounts.get(1));
        */
        /*
        System.out.println(accounts.get(0));
        accounts.get(0).addMatched(accounts.get(1));*/

        for(int i=0; i< 10;i++){
            accounts.get(0).addMatched(accounts.get(i).getUsername());
            accounts.get(i).addMatched(accounts.get(0).getUsername());
        }
        accounts.get(0).printAll();
        
        System.out.println(accounts.get(0).getMatched());
        System.out.println(accounts.get(1).getMatched());
        
        System.out.println(accounts.get(0).getPhoneNumber());
        System.out.println(accounts.get(0).getCourses());
        
        System.out.println(accounts.size());
        System.out.println(accounts.indexOf(new Account("", "SDaphne4", "", "","","",new ArrayList<String>(),1)));
        System.out.println(accounts.indexOf(new Account("", "MAndie4", "", "","","",new ArrayList<String>(),1)));
        /*
public boolean acceptAccount(String a)
public boolean rejectAccount(String a)
public boolean confirmMatch(String a)

    */
 /*     a.setEmail("new email");
        a.setMajor("nMajor");
        a.setName("neew name");
        a.setPassword("new Password");
        a.setPhoneNumber("elfk");
        a.setYear(20);
        System.out.println(a.getEmail()); 
        System.out.println(a.getMajor());
        System.out.println(a.getName());
        System.out.println(a.getPassword());
        System.out.println(a.getPhoneNumber());
        System.out.println(a.getUsername());
        System.out.println(a.getYear());

        System.out.println(a.getConfirmed()); 
        
        System.out.println(a.getPending()); 
        
        System.out.println(a.getRejected()); 
        
        System.out.println(a.getPreferences());
        
        System.out.println(a.getMatched());  
        
        System.out.println(a.getMatched()); 
        a.addRejected("haold");
        a.addRejected("datsh");
        a.addRejected("dlacf");
        
        System.out.println(a.getRejected()); 
        System.out.println(a.equals(b)); 
        System.out.println(a.getRejected().clone()); 
        /*
        System.out.println(a);*/
        
        int num=0;
        try {
            FileWriter myWriter = new FileWriter("AccountOutput.txt");
            for(int i=0;i<accounts.size();i++){
                Account a=accounts.get(i);
                
            String s=Account.createFile(a);
            myWriter.write(s);
            if(i>=81){
                System.out.print("s= "+s);
            }

            String [] info=s.split("//");
            new Account(info);

            if(i==accounts.size()-1){
                System.out.println(accounts.get(accounts.size()-1));
                System.out.println(s);
            }
            }
            
            
        try{
            //Find out how to scan file 
        scnr=new Scanner(new File("AccountOutput.txt"));
        
        while(scnr.hasNextLine()){
            String s=scnr.nextLine();        
                        
            num+=1;
            //System.out.println(num);
            if(num==80){
                System.out.println(s);
            }
            if(num==81){
                System.out.println(s);
            }
            String [] info=s.split("//");
            if(info.length!=12){
                break;
            }
            new Account(info);
        //System.out.println(accounts); 
        }
        
        //System.out.println(accounts); 
        scnr.close();
    }
        catch(FileNotFoundException e){
            System.out.println(e); }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } 
          catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
          
    }
}