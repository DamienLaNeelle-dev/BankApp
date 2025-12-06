function appData() {
    return {
        clients: [],
        loading: true,
        error: null,
        connectedClient: null,
        currentView: 'clients',
        selectedClientId: null,
        selectedClient: null,
        accounts: [],
        newAccount: { name: '', type: '', balance: 0 },

        // Initialise la page
        async init() {
            const token = localStorage.getItem('jwt');
            if (!token) {
                this.connectedClient = null;
                this.loading = false;
                return;
            }

            try {
                const res = await fetch('/api/auth/me', {
                    headers: { 'Authorization': `Bearer ${token}` }
                });

                if (!res.ok) {
                    localStorage.removeItem('jwt');
                    this.connectedClient = null;
                    this.clients = [];
                } else {
                    const client = await res.json();
                    this.connectedClient = client;
                    this.clients = client ? [client] : [];
                }
            } catch (err) {
                console.error(err);
                this.connectedClient = null;
                this.clients = [];
            } finally {
                this.loading = false;
            }
        },

        // Déconnexion
        logout() {
            localStorage.removeItem('jwt');
            this.connectedClient = null;
            this.clients = [];
            this.currentView = 'clients';
            window.location.href = '/login';
        },

        // Affiche les comptes d’un client
        async viewAccounts(clientId) {
            this.selectedClientId = clientId;
            this.currentView = 'accounts';
            this.error = null;

            try {
                this.selectedClient = this.connectedClient;
                const res = await fetch(`/api/clients/${clientId}/accounts`, {
                    headers: { 'Authorization': `Bearer ${localStorage.getItem('jwt')}` }
                });

                if (!res.ok) {
                    if (res.status === 401) {
                        alert("Session expirée, reconnectez-vous");
                        localStorage.removeItem('jwt');
                        window.location.href = '/login';
                        return;
                    }
                    if (res.status === 403) {
                        alert("Pas les droits pour voir ces comptes");
                        return;
                    }
                    throw new Error(`Erreur ${res.status}: Impossible de charger les comptes`);
                }

                this.accounts = await res.json();
            } catch (err) {
                console.error("Erreur viewAccounts:", err);
                this.error = err.message;
                alert(err.message);
            }
        },

        // Création d’un compte
        async createAccount() {
            // Validation simple
            if (!this.newAccount.name || !this.newAccount.type) {
                alert("Veuillez remplir le nom et le type du compte !");
                return;
            }

            if (this.newAccount.balance < 0) {
                alert("Le solde ne peut pas être négatif !");
                return;
            }

            try {
                const res = await fetch(`/api/clients/${this.selectedClientId}/accounts`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${localStorage.getItem('jwt')}`
                    },
                    body: JSON.stringify({
                        name: this.newAccount.name,
                        type: this.newAccount.type,
                        balance: parseFloat(this.newAccount.balance) // BigDecimal côté backend
                    })
                });

                if (!res.ok) {
                    const errorText = await res.text();
                    throw new Error(errorText || "Impossible de créer le compte");
                }

                const account = await res.json();
                this.accounts.push(account);
                this.newAccount = { name: '', type: '', balance: 0 };
                alert("Compte créé avec succès !");
            } catch (err) {
                console.error("Erreur createAccount:", err);
                alert("Erreur lors de la création du compte: " + err.message);
            }
        }
    };
}
