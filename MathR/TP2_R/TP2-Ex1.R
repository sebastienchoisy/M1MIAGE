# 1. On télécharge le csv et on le lit pour le stocker dans la variable mydata

mydata <- read.csv('data/wine-data.csv',TRUE,sep = ",")

# 2. On calcule le pourcentage de données absentes pour chaque colonne.

# On calcule le nombre total de lignes
TotalRowNumber <- nrow(mydata) 

# Colonne X (0 % de données absentes ou marquées comme NA)

missingDataXRow <- (sum(is.na(mydata$X) | mydata$X == "")/TotalRowNumber)*100

# Colonne country (0,05 % de données absentes ou marquées comme NA)

missingDataCountryRow <- (sum(is.na(mydata$country) | mydata$country == "")/TotalRowNumber)*100

# On formate le résultat

missingDataCountryRow <- round(missingDataCountryRow, digits=2)
missingDataCountryRow <-format(missingDataCountryRow, decimal.mark = ',')

# Colonne designation (28,83 % de données absentes ou marquées comme NA)

missingDataDesignationRow <- (sum(is.na(mydata$designation) | mydata$designation == "")/TotalRowNumber)*100

# On formate le résultat

missingDataDesignationRow <- round(missingDataDesignationRow, digits=2)
missingDataDesignationRow <-format(missingDataDesignationRow, decimal.mark = ',')

# Colonne points (0 % de données absentes ou marquées comme NA)

missingDataPointsRow <- (sum(is.na(mydata$points) | mydata$points == "")/TotalRowNumber)*100

# Colonne price (6,92 % de données absentes ou marquées comme NA)

missingDataPriceRow <- (sum(is.na(mydata$price) | mydata$price == "")/TotalRowNumber)*100

# On formate le résultat

missingDataPriceRow <- round(missingDataPriceRow, digits=2)
missingDataPriceRow <-format(missingDataPriceRow, decimal.mark = ',')

# Colonne province (0,05 % de données absentes ou marquées comme NA)

missingDataProvinceRow <- (sum(is.na(mydata$province) | mydata$province == "")/TotalRowNumber)*100

# On formate le résultat

missingDataProvinceRow <- round(missingDataProvinceRow, digits=2)
missingDataProvinceRow <-format(missingDataProvinceRow, decimal.mark = ',')

# Colonne region_1 (16,35 % de données absentes ou marquées comme NA)

missingDataRegion1Row <- (sum(is.na(mydata$region_1) | mydata$region_1 == "")/TotalRowNumber)*100

# On formate le résultat

missingDataRegion1Row <- round(missingDataRegion1Row, digits=2)
missingDataRegion1Row <-format(missingDataRegion1Row, decimal.mark = ',')

# Colonne region_2 (61,14% % de données absentes ou marquées comme NA)

missingDataRegion2Row <- (sum(is.na(mydata$region_2) | mydata$region_2 == "")/TotalRowNumber)*100

# On formate le résultat

missingDataRegion2Row <- round(missingDataRegion2Row, digits=2)
missingDataRegion2Row <-format(missingDataRegion2Row, decimal.mark = ',')

# Colonne taster_name (20,19 % de données absentes ou marquées comme NA)

missingDataTesterNameRow <- (sum(is.na(mydata$taster_name) | mydata$taster_name == "")/TotalRowNumber)*100

# On formate le résultat

missingDataTesterNameRow <- round(missingDataTesterNameRow, digits=2)
missingDataTesterNameRow <-format(missingDataTesterNameRow, decimal.mark = ',')

# Colonne taster_twitter_handle (24,02 % de données absentes ou marquées comme NA)

missingDataTesterTwitterHandleRow <- (sum(is.na(mydata$taster_twitter_handle) | mydata$taster_twitter_handle == "")/TotalRowNumber)*100

# On formate le résultat

missingDataTesterTwitterHandleRow <- round(missingDataTesterTwitterHandleRow, digits=2)
missingDataTesterTwitterHandleRow <-format(missingDataTesterTwitterHandleRow, decimal.mark = ',')

# Colonne title (0 % de données absentes ou marquées comme NA)

missingDataTitleRow <- (sum(is.na(mydata$title) | mydata$title == "")/TotalRowNumber)*100

# Colonne variety (0,0007 % de données absentes ou marquées comme NA)

missingDataVarietyRow <- (sum(is.na(mydata$variety) | mydata$variety == "")/TotalRowNumber)*100

# On formate le résultat

missingDataVarietyRow <-format(missingDataVarietyRow, decimal.mark = ',')

# Colonne winery (0 % de données absentes ou marquées comme NA)

missingDataWineryRow <- (sum(is.na(mydata$winery) | mydata$winery == "")/TotalRowNumber)*100

# 3. Calculez le pourcentage de lignes contenantes une valeur vide ou NA

RowsWithEmptiesOrNaValues <- (sum(rowSums(mydata =="" | is.na(mydata))>0)/TotalRowNumber)*100
RowsWithEmptiesOrNaValues <- round(RowsWithEmptiesOrNaValues, digits=2)
RowsWithEmptiesOrNaValues <-format(RowsWithEmptiesOrNaValues, decimal.mark = ',')
