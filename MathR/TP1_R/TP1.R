#2.Charger la base dans RStudio et la memoriser dans la variable ´ mydata

mydata <- read.csv('data/deaths-france-1899-1995.csv',TRUE,sep = ";")

#4.Calculer le nombre total de morts enregistres dans la base.

totalDeath <- sum(mydata$Total)

#totalDeath = 6 114 690
totalDeath_in_1900 <- sum(mydata[mydata$Year == '1900', 'Total'])