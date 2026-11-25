public class JobSearchService {
    public void search(String word) throws Exception {
        for (Job j : new JobRepository().all()) {
            if (j.getTitle().toLowerCase().contains(word.toLowerCase()))
                System.out.println(j);
        }
    }
}
