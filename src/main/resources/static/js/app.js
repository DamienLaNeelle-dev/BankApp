function clientsData(){
    return{
        clients: [],
        loading: true,
        error: null,

        init(){
            fetch('/api/clients')
                .then(r => r.json())
                .then(data => {
                    console.log(data);
                    this.clients = data;
                    this.loading = false
                })
                .catch(e => {
                    this.error = e.message;
                    this.loading = false;
                })
        }
    }
}