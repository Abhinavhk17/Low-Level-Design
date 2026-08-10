class LFUCache {
    class Node{
        Node next;
        Node prev;
        int key;
        int value;
        int freq;

        Node(int key, int value, int freq){
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }

    class  DLL{
        Node head,tail;
        int size = 0;

        DLL(){
            head = new Node(0,0,0);
            tail = new Node(0,0,0);
            head.next = tail;
            tail.prev = head;
        }

        private void insert(Node node){
            node.next = head.next;;
            node.prev = head;
            node.next.prev = node;
            head.next = node;
            size++; 
        }

        private void remove(Node node){
            node.prev.next =  node.next;
            node.next.prev = node.prev;
            size--;
        }

        private Node  removeLast(){
            if(size == 0) return null;
            Node node = tail.prev;
            remove(node);
            return node;
        }
    }
    
    int minfreq;
    int capacity;
    
    Map<Integer, Node> keyToNode = new HashMap<>() ;

    Map<Integer, DLL> freqMap = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!keyToNode.containsKey(key)) return -1;
        Node node = keyToNode.get(key);
        addFreqDLL(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if(capacity == 0) return;

        if(keyToNode.containsKey(key)){
            Node node = keyToNode.get(key);
            node.value = value;
            addFreqDLL(node);
            return;
        }

        if(keyToNode.size() >= capacity){
            DLL minlist = freqMap.get(minfreq);
            Node evict = minlist.removeLast();
            keyToNode.remove(evict.key);
        }

        Node node = new Node(key, value, 1);
        keyToNode.put(key,node);
        freqMap.computeIfAbsent(1 , k -> new DLL()).insert(node);
        minfreq = 1;
    }

    private void addFreqDLL(Node node){
        DLL oldList = freqMap.get(node.freq);
        oldList.remove(node);

        if(node.freq == minfreq && oldList.size == 0){
            minfreq++;
        }

        node.freq++;
        freqMap.computeIfAbsent(node.freq, k -> new DLL()).insert(node);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
