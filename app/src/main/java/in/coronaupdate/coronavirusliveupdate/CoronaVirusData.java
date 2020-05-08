package in.coronaupdate.coronavirusliveupdate;

public class CoronaVirusData {
   String CountryName;
   String  TotalDeath;
   String FlagUrl;
   String TotalRecovered;
   String TotalCases;
   String Todaycases;
   String Todaydeads;

    public String getTodaycases() {
        return Todaycases;
    }

    public void setTodaycases(String todaycases) {
        Todaycases = todaycases;
    }

    public String getTodaydeads() {
        return Todaydeads;
    }

    public void setTodaydeads(String todaydeads) {
        Todaydeads = todaydeads;
    }

    public CoronaVirusData() {
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getTotalDeath() {
        return TotalDeath;
    }

    public void setTotalDeath(String totalDeath) {
        TotalDeath = totalDeath;
    }

    public String getFlagUrl() {
        return FlagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        FlagUrl = flagUrl;
    }

    public String getTotalRecovered() {
        return TotalRecovered;
    }

    public void setTotalRecovered(String totalRecovered) {
        TotalRecovered = totalRecovered;
    }

    public String getTotalCases() {
        return TotalCases;
    }

    public void setTotalCases(String totalCases) {
        TotalCases = totalCases;
    }
}
