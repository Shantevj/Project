package com.shantev.useful;

public enum Category {
        SCIENCE(1), IT(2), TECH(3), BUSSINES(4), ENVIRO(5), HEALTH(6), SOCIETY(7), LIFE(8), OTHER(9);
        private int categoryId;
        Category(int categoryId) {
            this.categoryId = categoryId;
        }
        public int getCategoryId() {
            return categoryId;
        }
}
