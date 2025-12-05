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

function accountsData(clientId){
    return {
        clientId: clientId,
        accounts: [],
        error: null,

        init() {
            console.log("ClientId : ", this.clientId)
            fetch(`/api/clients/${this.clientId}/accounts`)
                .then(r => r.json())
                .then(data => {
                    this.accounts = data;
                })
                .catch(e => {
                    this.error = e.message;
                });
        }

    }
}




