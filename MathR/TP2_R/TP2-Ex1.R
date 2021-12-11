
mydata <- read.csv('data/wine-data.csv',TRUE,sep = ",")

test <- sum(is.na(mydata$X)) + sum(is.empty(mydata$X))