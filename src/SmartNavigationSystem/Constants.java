package SmartNavigationSystem;

public class Constants {

    public static final int vertexNumber = 20;

    public static final String[][] vertices_info = new String[][] {
        {"0", "Sha Tin Che Kung Temple", "The ancient Sha Tin Che Kung Temple draws believers from all over Hong Kong. Every Chinese New Year, worshippers flock to the Temple. In recent years, it has even become a popular site of attraction among travellers visiting Hong Kong. Although it is impossible to know when it was built, the stele in the old temple structure says it was renovated and reconstructed in the 16th year of the reign of Guangxu in the Qing Dynasty (1890).", "500 metres"},
        {"1", "Kwok Tak Seng Catholic Secondary School"},
        {"2", "Hong Kong Heritage Museum", "The Hong Kong Heritage Museum presents a unique mix of history, art and culture and has a great variety of programmes catering for the wide-ranging interests of the general public. 12 exhibition halls are accommodated inside, with an exhibition area of some 7,500 square metres. The museum houses five permanent galleries - the Jin Yong Gallery, the Cantonese Opera Heritage Hall, the T.T. Tsui Gallery of Chinese Art, the Chao Shao - an Gallery and the Children's Discovery Gallery. It provides visitors with an inspiring, educational and enjoyable museum experience, and encourages the public to pursue knowledge, art, and creativity.", "600 metres"},
        {"3", "Lei Uk Tsuen"},
        {"4", "Chui Tin Street Soccer Pitch"},
        {"5", "Tsang Tai Uk", "Tsang Tai Uk, one of the best-preserved Hakka walled villages in Hong Kong, is just a short distance away from MTR Che Kung Temple Station. Built in 1847 by Tsang Koon-man, a stonemason, the compound was home to the Tsang clan, a Hakka family that migrated to Hong Kong in the 17th century. Guard towers were built in the four corners of the village for protection against rampant piracy in the early 20th century. The granite, blue brick, and wood used to build the village are still preserved. This is a living village, and visitors are allowed to explore the courtyard and the ancestral hall.", "300 metres"},
        {"6", "Badminton court"},
    };

    public static final int[][] weight = new int[][] {
        {0, 1, 1, 2},
        {0, 2, 1, 2},
        {0, 3, 1, 2},
        {1, 4, 1, 2},
        {2, 4, 1, 2},
        {2, 5, 1, 2},
        {3, 5, 1, 2},
        {4, 6, 1, 2},
        {5, 6, 1, 2}
    };
}
