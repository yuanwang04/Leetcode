class WordDictionary {

   class LetterNode {
      boolean used;
      LetterNode[] next;
      
      public LetterNode(boolean b) {
         this.used = b;
         next = new LetterNode[26];
      }
   }
   
   private LetterNode head;
   
   /** Initialize your data structure here. */
   public WordDictionary() {
      head = new LetterNode(false);
   }
   
   /** Adds a word into the data structure. */
   public void addWord(String word) {
      LetterNode curr = head;
      for (int i = 0; i < word.length(); i++) {
         int n = word.charAt(i) - 'a';
         if (curr.next[n] == null) {
            curr.next[n] = new LetterNode(false);
         }
         curr = curr.next[n];
      }
      curr.used = true;
   }
   
   /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
   public boolean search(String word) {
      return search(word, head);
   }
   
   public boolean search(String word, LetterNode curr) {
      if (curr == null) 
         return false;
      
      if (word.isEmpty()) {
         return curr.used;
      }
      
      
      if (word.charAt(0) == '.') {
         for (int i = 0; i < 26; i++) {
            if (curr.next[i] != null && 
            search(word.substring(1), curr.next[i])) {
               return true;
            }
         }
         return false;
      } else {
         int n = word.charAt(0) - 'a';
         return search(word.substring(1), curr.next[n]);
      }
   }
}