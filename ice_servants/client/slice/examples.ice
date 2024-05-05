
module Demo {
    interface DedicatedService {
        string sayHello(string name);
    };

    interface SharedService {
        string getTime();
    };
};
