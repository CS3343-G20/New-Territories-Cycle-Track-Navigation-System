package SmartNavigationSystem;

public final class Constants {

        public static final int vertexNumber = 20;

        public static final String[][] vertices_info = new String[][] { 
                { "0", "Sha Tin Che Kung Temple", "500 metres", "a popular ancient temple drawing worshippers" }, 
                { "1", "Kwok Tak Seng Catholic Secondary School" },
                { "2", "Hong Kong Heritage Museum", "600 metres", "a unique mix of history, art and culture with a great variety of exhibitions" },
                { "3", "Lei Uk Tsuen" }, 
                { "4", "Chui Tin Street Soccer Pitch" },
                { "5", "Tsang Tai Uk", "300 metres", "one of the best-preserved living Hakka walled villages in Hong Kong" },
                { "6", "Badminton court" }, 
                { "7", "Sheung Shui" },
                { "8", "Shatin" },
                { "9", "Yuen Long" },
        };

        public static final String[][] climbingTrails_info = new String[][] {
                {"1", "1", "7", "Sheung Shui", "Tai Po"},
                {"2", "3", "8", "Shatin", "Sheung Shui"},
                {"3", "3", "9", "Yuen Long", "Tuen Muen"}
        };

        public static final int[][] weight = new int[][] { 
                { 0, 1, 1, 2 }, 
                { 0, 2, 1, 2 }, 
                { 0, 3, 1, 2 }, 
                { 1, 4, 1, 2 },
                { 1, 7, 4, 3 },
                { 2, 4, 1, 2 }, 
                { 2, 5, 1, 2 }, 
                { 3, 5, 1, 2 }, 
                { 3, 8, 2, 3 },
                { 3, 9, 3, 1 },
                { 4, 6, 1, 2 }, 
                { 4, 9, 2, 1 },
                { 5, 6, 1, 2 },
                { 5, 7, 2, 3 },
                { 6, 8, 3, 2 }
        };
}
